import gpt_router

from fastapi import FastAPI

from starlette.middleware.cors import CORSMiddleware

app = FastAPI()

# CORS 미들웨어 설정 (React 앱과의 통신을 허용하기 위해 필요)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # React 앱의 주소에 따라 조정
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
def hello():
    return "hello FASTAPI!!"

app.include_router(gpt_router.router)

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)