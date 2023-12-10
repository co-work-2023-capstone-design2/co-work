## CO-WORK: 가상 모임 플랫폼

### 개요
멀리 떨어진 사용자들이 인터넷 공간에 모여 소통할 수 있는 웹사이트를 제작한다.

가벼운 모임 플랫폼에서 추후 협업 플랫폼으로의 활용 가능성을 고려하여 웹사이트의 이름을“CO-WORK”로 한다.

CO-WORK는 로그인 후 사용할 수 있다.

모든 사용자는 개별 배경 맵을 가진다. 맵에 자신이 생성한 모임의 건물을 위치시킬 수 있다. 

여러 모임을 생성할 수 있고, 다른 사용자가 생성한 모임에 초대 코드를 통해 기존 모임에 참여할 수 있다.
![맵 예상 이미지](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/3d3679ad-2fa2-48c9-a169-a5916fdbae7e)

- CO-WORK에서 제공하는 기능은 다음과 같다.
  
1) 모임 생성
모임 이름과 설명을 작성하여 새로운 모임을 생성한다.

2) 모임 건물 개인 맵에 위치시키기
자신이 생성한 모임의 건물을 사용자별로 제공되는 개인 맵에 위치시킨다. 맵 위에 있는 건물을 클릭하면 자신이 생성한 모임에 참여할 수 있다. 

3) 기존 모임 참여
기존 모임의 초대 코드를 입력하여 다른 사용자의 모임에 참여할 수 있다.

4) 모임 내부 캐릭터 이동
사용자는 자신의 가상 캐릭터를 통해 모임에 참여하고, 모임 내부를 캐릭터로 돌아다닐 수 있다. 

5) 모임 내부 채팅
참여한 모임 내부에서 다른 사용자와 채팅을 통해 소통할 수 있다.

6) 모임 내부 todo-list 설정
모임 전체의 목표와 개인 목표를 모임 내부의 todo-list 창을 통해 설정할 수 있다.

7) 모임 공간 꾸미기
자신이 생성한 모임의 경우 모임 내부 공간을 꾸밀 수 있다.
![공간 꾸미기 예상 이미지](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/0e68d33a-4570-47d6-878c-332ed5eb0f70)

### 프로젝트 진행 과정
- 기간
2023.09. ~ 2023.12.

- 사용 기술
![image](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/75c003e6-1f5f-4b02-8a63-3fc37bdcb2cb)

- 진행 일정
![WBS](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/a1351986-6042-4b9c-859e-688bbc5f4506)

- 레이아웃 구성

1) 회원가입/로그인
사용자가 자신의 이름, 이메일, 비밀번호를 입력하여 웹사이트에 회원 정보를 등록한다. 자신의 회원 정보를 통해 로그인하여 웹사이트를 사용한다.

2) 모임 생성 화면
자기 소유의 모임을 생성하는 화면이다. 초대 코드는 무작위로 생성되며, 모임의 이름과 설명을 작성하여 모임을 생성한다.

3) 초대 코드 입력 화면
초대 코드 입력 창에 기존 모임의 초대 코드를 입력한다. 초대 코드에 해당하는 모임의 이름, 설명 정보를 사용자에게 보여준다.

4) 캐릭터 설정 화면
모임 내에서 사용할 사용자의 캐릭터를 설정하는 화면이다.

5) 모임 내부 화면
캐릭터 설정 화면에서 설정한 캐릭터로 모임 내부를 키보드로 이동시킬 수 있는 화면이다. 모임 또는 자신의 계획 설정이 가능하며, 모임에 참여한 다른 사용자와의 소통이 가능한 화면이다.

- ERD
![image](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/3b556faa-34e1-4df9-80bb-3e725280e63c)

- 웹사이트 흐름
![image](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/c7293dd2-18e3-44e2-bf21-a5502f919b59)

- 서버 구조
![image](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/f6531573-ac1e-488c-9b54-e9f493ff34ab)

- API 명세서
![image](https://github.com/co-work-2023-capstone-design2/co-work/assets/86397095/84c6696f-17a2-4a51-a3f4-f79b3a02ad67)

### 전체 동작 영상
https://clipchamp.com/watch/uF1WpiPsQEL 
