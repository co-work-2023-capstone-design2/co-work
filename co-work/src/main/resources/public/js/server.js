// 회원관리
const register = "http://localhost:5500/api/auth/signup";
const login = "http://localhost:5500/api/auth/login";

// 모임 생성
const getMeetingCode = "http://localhost:5500/api/gathering/createGatheringCode";
const setMeeting = "http://localhost:5500/api/gathering/createGathering";
const setLocation = "http://localhost:5500/api/gathering/setLocation";
const getInviteInfo = "http://localhost:5500/api/gathering/getGatheringInfoByCode/";

// 캐릭터 생성
const setCharacter = "http://localhost:5500/api/member/createCharacter";

// 웹소켓 연결
const socketUrl = 'ws://localhost:5500/ws/chat';