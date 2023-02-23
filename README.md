
## firebase 의 ChatBot 기능을 구현한 앱  
#### ✔  firebase 의 Authentication 을 이용하여 회원가입된 회원만이 앱을 이용할 수 있도록 하였고,  
#### ✔  클라우드에 호스팅되는 noSQL 데이터베이스로 데이터를 저장하고 사용자와 기기 간에 실시간으로 동기화를 해주는 firebase의 Realtime Database 이용하여 데이터를 저장하였다.  
#### 챗봇 기능을 구현한 내용을 토대로 기술 세미나 진행  

##  
#### 📢 기능 구현 :  
#### 회원가입, 로그인, 로그아웃  
#### 채팅방 개설 및 입장, 채팅방에 접속하여 채팅하기, 메인화면에서 리스트뷰의 개설된 채팅방 클릭시 그 채팅방으로 이동  
#  


#### 📌 회원가입, 로그인  
firebase 의 createUserWithEmailAndPassword 함수를 이용하여서 회원가입 기능 구현  
firebase 의 signlnWithEmailAndPassword 함수를 이용하여서 로그인 기능 구현  
  
![image](https://user-images.githubusercontent.com/104052659/220808706-985760fd-ac17-41ee-90b4-9c2d3e133d0d.png)
![image](https://user-images.githubusercontent.com/104052659/220808687-bef6debf-fea9-4ec1-bad1-095a4e3ed202.png)

#  
#### 📌 채팅방 입장 또는 새로 만들기 & 개설된 채팅방 클릭시 그 채팅방으로 이동
![image](https://user-images.githubusercontent.com/104052659/220808794-9942092e-4d6d-43fc-920b-e236dd213ad8.png)

#  
#### 📌 채팅방에서 채팅 보내는 기능, 액션바에 뒤로가기 버튼  
![image](https://user-images.githubusercontent.com/104052659/220808828-a57b901b-bd15-4777-9fa8-f41290929cae.png)
![image](https://user-images.githubusercontent.com/104052659/220808839-c1478e8e-045b-45dd-ad84-6a8d7395b8ba.png)


#  
#### 📌 로그아웃  
![image](https://user-images.githubusercontent.com/104052659/220808855-d3fec0a1-34f6-46f7-b7ad-e612f4520906.png)
#  
#### 📌 realtime database 에 데이터가 추가된 결과  
![image](https://user-images.githubusercontent.com/104052659/220808454-3fd55947-feea-41c6-aec7-5d6c6b9bc1f2.png)
