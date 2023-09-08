# 📖 TCP/IP 흐름 제어 및 혼잡 제어

## 📌 TCP 통신

<br>

### ✅ TCP란?
<br>


#### TCP(Transmission Control Protocol)

> 서버와 클라이언트 간 데이터를 <span style="color:red">**신뢰성**</span> 있게 전달하기 위해 만들어진 프로토콜

- 데이터를 전송하기 전에 데이터 전송을 위한 연결을 만드는 `연결지향 프로토콜`

- 데이터는 전달되는 과정에서 손실되거나 순서가 뒤바뀌어 전달될 수 있다.
	- TCP는 이러한 손실을 검색하여 이를 교정하고 순서를 재조합하도록 해준다.
    - 하위 계층에서 온 데이터를 위의 과정을 통해 <span style="color:yellow">**전송 문제를 검출하고 해결해준다!**</span>

- TCP/IP 4 계층 중, Transport Layer의 TCP는 Internet Layer의 IP 위에 올라가서 IP의 단점(비연결성, 전송제어 정보 X)에 대한 보완을 해준다.
	
    - 이러한 통신 방식을 TCP/IP 통신이라 부르는 것!

<br><br>

### ✅ TCP의 역할

<br>

- 받을 대상 노드(호스트)가 서비스 가능 상태(연결 가능)인지 확인하고 연결을 수립하는 역할
	
    - 3 Way Handshake를 통한 연결 수립 (차후 공부할 내용!)

- 전송을 제어해주는 정보를 패킷에 추가해주는 역할
	
    - Port 정보, 순서 정보 등의 정보를 전송을 제어해주는 프로토콜의 패킷에 추가한다.
    	
        - 순서 정보 : 패킷이 여러개로 나뉘어져 있을 때 각 패킷이 어떤 순서로 처리되어야 하는지에 대한 정보
        
        
 - 전송을 받았을 때 어떻게 처리해야 하는지에 대한 정보 추가
 	
    - 중간에 빠진 패킷이 있거나 정보가 있을 때 요청자에게 해당 패킷을 다시 보내라고 요청한다.
    	
        - 데이터 전달 보증

<br><br>

### ✅ TCP의 구조

![](https://velog.velcdn.com/images/yun12343/post/c6202e44-563d-49c4-8105-9e551ad54ba3/image.png)

1. Source Port(발신지 포트) : 패킷을 송신하는 시스템의 포트 번호
 
2. Destination Port(목적지 포트) : 패킷을 수신할 시스템의 포트 번호
 
3. Sequence Number(순차 번호) : 각 Segment의 첫 Byte에 부여되는 번호
 
4. Acknowledge Number(응답 확인 번호) : 수신한 세그먼트의 확인 응답 용도

5. Header Length(헤더 길이) : TCP의 헤더 길이를 나타내는 필드

6. TCP Control Flag (TCP 제어 플래그) : 각 필드의 흐름 제어, 종료, 데이터 전송 모드 구성
	
    - URN(긴급플래그)
    - ACK(응답플래그)
    - PSH(Puch플래그)
    - RST(Reset플래그)
    - SYN(연결요청플래그)
    - FIN(Finish플래그)

7. Window Size (윈도우 크기) : 수신 윈도우의 보낼 수 있는 데이터의 양

8. Checksum(검사합) : 데이터가 전송 중에 손실되지 않고 원본가 동일한지 검사

9. Urgent Point(긴급 포인터) : Segement가 긴급 데이터를 포함할 때 알림

<br><br>

### ✅ TCP의 특징

1. <span style="color:red">**신뢰성**</span> (데이터 손상/누락 체크 + 순서 체크)

- TCP의 가장 중요한 특징

- 신뢰할수 있는 말단 장치 간 데이터 전달
	
    - 신뢰성을 확보하기 위해, TCP는 손상되거나, 중복되거나, 순서가 뒤틀린 데이터를 복구 한다.
    - 그러기 위해 적극적 수신, 통지, 재전송 체계를 사용한다.

<br><br>

2. <span style="color:red">**흐름 제어**</span>

- 컴퓨터는 CPU와 네트워크 대역폭 간의 차이에 의해 <span style="color:yellow">**서로 다른 데이터 속도**</span>로 작동할 수 있다.
	
    - TCP는 송신자가 보낸 데이터의 양을 제어하는 흐름 제어 메커니즘을 구현한다.

<br><br>

3. <span style="color:red">**다중화**</span>

- TCP 다중화 : TCP에서는 한 라우터의 수많은 프로세스가 TCP 통신 서비스를 <span style="color:red">**동시에**</span> 사용할 수 있다.
	
    - 이 프로세스들은 같은 네트워크 인터페이스에서 통신 가능하다. => 네트워크 인터페이스의 IP 주소로 식별
    - but> 컴퓨터에서 같은 네트워크 인터페이스를 사용하는 모든 프로세스는 공통의 IP 주소를 가진다.
    
        - 따라서 TCP는 TCP를 사용하는 응용프로그램에 포트번호 값을 연계시키고, 이렇게 되면 각 연결이 서로 다른 포트 쌍을 사용하게 된다.
        
        - 이러한 포트 바인딩에 의해 응용 프로그램의 프로세스들 사이에 여러 연결이 존재 가능하게 된다.

<br><br>

4. <span style="color:red">**연결형 서비스**</span>

- 응용 프로그램 프로세스는 TCP를 사용하여 데이터를 보내기 위해 먼저 연결을 설정해야 한다.

<br><br>

5. <span style="color:red">**양방향 운반**</span>

- 하나의 전송선로에서 데이터가 동시에 양방향으로 전송될 수 있다.

<br><br>

6. <span style="color:red">**3 way handshake**</span>
	
- 연결을 설정할 때 동기화(SYN) 제어 플래그를 이용하며, 3 way handshake라는 세 메시지를 교환하게 된다.
	
    - 차후 글에서 배우게 될것!

<br><br>

## 📌 흐름 제어 (Flow Control)

<br>

### ✅ 흐름 제어

- 수신 측 데이터 처리 속도 > 송신 측 데이터 처리 속도
	
    - 상관 없음
    
- 수신 측 데이터 처리 속도 < 송신 측 데이터 처리 속도
	
    - <span style="color:yellow">**수신 측에서 제한된 저장 용량을 초과한 이후 도착하는 패킷은 손실될 수 있다.**</span>
    	
        - 손실 시 불필요한 추가 패킷 전송 발생

- 흐름제어는 위 문제 처럼, 송신 측과 수신 측의 TCP 버퍼 크기 차이로 인해 생기는 데이터 처리 속도 차이를 해결하기 위한 기법
	
    - TCP 버퍼 : 송신 측은 버퍼에 TCP 세그먼트를 보관한 후 순차적으로 전송하고, 수신 측은 도착한 TCP 세그먼트를 애플리케이션이 읽을 때까지 버퍼에 보관한다.

- 이러한 위험을 해결하기 위해 <span style="color:yellow">**송신 측의 데이터 전송량을 수신측에 따라 조절해야 한다.**</span>

<br><br>

### ✅ 흐름 제어 기법 1 : Stop and Wait

![](https://velog.velcdn.com/images/yun12343/post/884544b2-e486-4513-9786-b6ebb156fb53/image.png)


- <span style="color:yellow">**전송한 패킷에 대해 확인 응답(ACK)를 받게 되면 다음 패킷을 전송하는 방식**</span>
	
    - 일일이 패킷을 하나씩 보내기 때문에 비효율적이다.

<br><br>

### ✅ 흐름 제어 기법 2 : Sliding Window


- <span style="color:yellow">**수신 측에서 설정한 윈도우 크기만큼 송신 측에서 확인 응답(ACK) 없이 패킷을 전송할 수 있게 한다.**</span>

- 데이터의 흐름을 동적으로 조절하는 방식이다.

<br>

- 윈도우 : 메모리 버퍼의 일정 영역. 모든 TCP/IP를 사용하는 호스트들은 송신과 수신을 위한 2개의 Window를 가지고, 데이터를 보내기 전에 수신 측과 송신 측이 3 way handshaking을 통해 각 window size를 맞도록 조절한다.

<br>

![](https://velog.velcdn.com/images/yun12343/post/a9e810b7-889c-4a7a-a01f-584bd4df9066/image.png)

- 동작 방식 : 윈도우에 포함되는 모든 패킷 전송 -> 패킷 전달이 확인되면(수신 측에서 확인 응답(ACK)를 다시 보내오면) 윈도우를 옆으로 옮겨 다음 패킷을 전송

<br>

- 송신 측은 일정 시간동안 수신측으로부터 확인 응답(ACK)을 받지 못하면 패킷을 재전송한다.
	
    - 그런데 사실 패킷이 손실된 것이 아니라, 수신측 버퍼에 공간이 없어서 못받고 있는 것이였다면 ??
    	
        - 이를 해결하기 위해 송신 측은 확인 응답(ACK)를 보내면서 남은 버퍼의 크기(윈도우 크기)도 함께 첨부하여 전송하여 수신측이 상황을 잘 파악할 수 있도록 한다.

<br><br>

## 📌 혼잡 제어

<br>

### ✅ 혼잡 제어

- 데이터의 양이 라우터가 처리 가능한 양을 넘어설 때, 송신 측에서는 처리 못한 데이터를 손실 데이터로 간주하고, 계속 재전송하게 된다.
	
    - 이는 네트워크를 혼잡하게 한다.
    	
        - <span style="color:yellow">**네트워크 내 혼잡한 상황을 해결하기 위해, 송신 측의 전송속도를 적절히 조절하는데, 이를 혼잡 제어 라고 한다. **</span>

<br><br>

### ✅ 혼잡 제어 기법 1 : AIMD (Additive Increase/Multicative Decrease)

- 처음 패킷을 하나 보내고 나서 <span style="color:yellow">**문제가 없이 도착하면 윈도우의 크기를 1씩 증가**</span>시키면서 전송한다.

- <span style="color:yellow">**전송에 실패했다면 윈도우의 크기를 반으로 줄인다.**</span>

![업로드중..](blob:https://velog.io/b02ee45b-3d90-412c-8018-35d0b27aefe7)

- 단점 : 윈도우 크기가 1씩만 증가하기에, 적절한 크기, 속도에 도달하여 통신하기 까지 너무 오래걸린다.

<br><br>

### ✅ 혼잡 제어 기법 2 : Slow Start (느린 시작)

![업로드중..](blob:https://velog.io/4772078e-4ee4-48b6-bd47-2119201bb2d1)


- 윈도우의 크기를 <span style="color:yellow">**1, 2, 4, 8 ..**</span> 의 형식으로 증가시키다가, <span style="color:yellow">**혼잡이 감지되면 윈도우의 크기를 1로**</span> 바꾼다.

- 처음엔 느려도, 나중엔 매우 증가폭이 커서 점점 빨라진다.

<br><br>

### ✅ 혼잡 제어 기법 3 : Fast Retransmit (빠른 재전송)

- 수신 측에서 세그먼트로 분할된 내용들이 올바른 순서대로 도착하지 않는 경우가 있을 수 있다.
	
    - <span style="color:yellow">**수신 측에서 순서대로 잘 도착한 마지막 패킷의 다음 순번을 ACK (확인 응답) 패킷에 실어서 보낸다.**</span>
  	- 이 패킷을 <span style="color:yellow">**같은 것을 3개 받게 된다면, 순서에 문제가 생겼다고 판단, 재전송이 이루어진다.**</span>
  
- 송신 측에서 기존에 설정한 타임 아웃 시간보다 빠르게 문제를 파악할 수 있어서 빠른 문제 해결이 가능하다.

<br><br>

### ✅ 혼잡 제어 기법 4 : Fast Recovery (빠른 회복)

- 혼잡 상태가 될 때, 윈도우 크기를 1로 줄이는 것이 아니라, <span style="color:yellow">**반으로 줄인 후, 그 이후부터는 1씩 증가시킨다.**</span>
	
    - 한번 혼잡 상태를 겪고 나면, 그 다음부터는 AIMD 방식으로 동작하게 된다.

<br><br>

### ✅ 혼잡 제어 단어 정리 1 : Timeout

- 송신 측에서 보낸 데이터가 유실되었거나, 수신 측이 응답으로 보낸 ACK 패킷이 유실된 경우

<br><br>

### ✅ 혼잡 제어 단어 정리 2 : 3 ACK Duplicate

- 위의 Fast Retransmit (빠른 재전송) 기법에서 나온 중복 ACK 3개. 해당 패킷에는 다음 순번이 적혀있다.
	
    - 이 3개의 중복 ACK가 도착하면 송신 측에서 순서가 잘못되었구나 라고 느낀다.

<br><br>

### ✅ 혼잡 제어 단어 정리 3 : Slow Start 임계점 (ssthresh)

- Threshold : 임계점

- 이 임계점 까지만 Slow Start 기법을 사용하겠다는 뜻이다.
	
    - 이 임계점이 넘어가게 되면, AIMD 기법을 사용하게 된다.

### ✅ 혼잡 제어 정책

- 모든 혼잡 제어 정책들은 공통적으로 혼잡이 발생하면 윈도우 크기를 줄이거나, 증가시키지 않는 것으로 혼잡을 회피한다.

- 혼잡 제어 정책들은 위에서 설명한 혼잡 제어 기법들을 섞어서 활용한다.

ex> TCP Tahoe, TCP Reno

TCP Tahoe : 처음에는 Slow Start -> 임계점(ssthresh)를 넘으면 AIMD로 전환 -> ACK Duplicated / Timeout 발생 시 ssthresh, 윈도우 크기 수정

TCP Reno(TCP Tahoe 이후 정책) : Slow Start -> 임계점 넘으면 AIMD ->  TCP Tahoe와 다르게 ACK Duplicated 일때는 AIMD처럼 반으로 줄인다. Timeout을 만나면 Slow Start 로 전환한다.

<br><br>

### ✅ 흐름 제어 vs 혼잡 제어

- 흐름 제어 : 송수신측 사이의 패킷 수 제어

- 혼잡 제어 : 네트워크 내 패킷 수 조절


<br><br>

## 📚 출처

- https://gyoogle.dev/blog/computer-science/network/%ED%9D%90%EB%A6%84%EC%A0%9C%EC%96%B4%20&%20%ED%98%BC%EC%9E%A1%EC%A0%9C%EC%96%B4.html
- https://musclebear.tistory.com/2
- https://jddng.tistory.com/191
- https://steady-coding.tistory.com/507

<br><br>
