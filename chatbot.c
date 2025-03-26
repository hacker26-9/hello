#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

#define API_KEY "YOUR_GEMINI_API_KEY"
#define API_URL "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" API_KEY

struct Memory {
    char *response;
    size_t size;
};

size_t write_callback(void *ptr, size_t size, size_t nmemb, void *userdata) {
    size_t real_size = size * nmemb;
    struct Memory *mem = (struct Memory *)userdata;
    mem->response = realloc(mem->response, mem->size + real_size + 1);
    if (mem->response == NULL) return 0;
    memcpy(&(mem->response[mem->size]), ptr, real_size);
    mem->size += real_size;
    mem->response[mem->size] = '\0';
    return real_size;
}

void chat_with_gemini(const char *user_input) {
    CURL *curl;
    CURLcode res;
    struct Memory chunk = {malloc(1), 0};

    curl_global_init(CURL_GLOBAL_ALL);
    curl = curl_easy_init();

    if (curl) {
        char post_data[1024];
        snprintf(post_data, sizeof(post_data),
                 "{\"contents\": [{\"parts\": [{\"text\": \"%s\"}]}]}",
                 user_input);

        struct curl_slist *headers = NULL;
        headers = curl_slist_append(headers, "Content-Type: application/json");

        curl_easy_setopt(curl, CURLOPT_URL, API_URL);
        curl_easy_setopt(curl, CURLOPT_POST, 1L);
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, post_data);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&chunk);

        res = curl_easy_perform(curl);

        if (res == CURLE_OK) {
            printf("Chatbot: %s\n", chunk.response);
        } else {
            fprintf(stderr, "Request failed: %s\n", curl_easy_strerror(res));
        }

        curl_easy_cleanup(curl);
        curl_slist_free_all(headers);
    }

    free(chunk.response);
    curl_global_cleanup();
}

int main() {
    char user_input[256];
    printf("Gemini Chatbot initialized. Type 'exit' to quit.\n");

    while (1) {
        printf("You: ");
        fgets(user_input, sizeof(user_input), stdin);
        user_input[strcspn(user_input, "\n")] = 0;
        
        if (strcasecmp(user_input, "exit") == 0 || strcasecmp(user_input, "quit") == 0) {
            printf("Chatbot: Goodbye!\n");
            break;
        }
        
        chat_with_gemini(user_input);
    }
    return 0;
}
