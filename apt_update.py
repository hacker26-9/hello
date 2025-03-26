import google.generativeai as genai

# Configure API key
genai.configure(api_key="AIzaSyDNVBWwOggYgwmrFNS3kC61gj7uENvr26E")

# Initialize Gemini model
model = genai.GenerativeModel("gemini-pro")

# Simple chat loop
while True:
    user_input = input("You: ")
    if user_input.lower() in ["exit", "quit"]:
        print("Chatbot: Goodbye!")
        break
    response = model.generate_content(user_input)
    print("Chatbot:", response.text)
