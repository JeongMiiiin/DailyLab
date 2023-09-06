from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
import openai
from starlette.middleware.cors import CORSMiddleware

openai.api_key = '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'

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # React 앱의 주소에 따라 조정
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class Request(BaseModel):
    diary: str

class Response:
    def __init__(self, request: Request):
        self.response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                        {"role": "system", "content": "당신은 다음과 같은 특징을 가진 사람이야"
                                      "성격 : 내향형"
                                      "성별 : 남자"
                                      "생년월일 : 1995-02-06"
                                      "이루고 싶은 목표 : 취업하기"
                                      "좋아하는 음식 : 피자"
                                      "좋아하는 여행지 : 뉴욕"
                        },
                        {"role": "user", "content": request.diary }, 
            ],
            temperature=1, 
            max_tokens=1500,
            top_p=1, 
            frequency_penalty=0.0,
            presence_penalty=0.0, 
        )

    def getDiary(self):
        return {"data" : self.response['choices'][0]['message']['content']}

@app.post("/diary")
def diary(request: Request):
    return Response(request).getDiary()

if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)