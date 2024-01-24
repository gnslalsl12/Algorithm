const GhostList = [];
const Deltas = [
  [0, 1],
  [1, 0],
  [0, -1],
  [-1, 0],
  [0, 0], //석준이가 현재 위치에서 1초 기다리는 것도 가능
];
let Map;
let N, M;
let Ntemp = 0;
let Sx, Sy, Ex, Ey;

const init = (line) => {
  if (N === undefined) {
    [N, M] = line.split(" ").map(Number);
  } else if (Sx === undefined) {
    Map = Array.from(new Array(4), () => Array.from(new Array(N), () => new Array(M).fill("O")));
    [Sx, Sy, Ex, Ey] = line
      .split(" ")
      .map(Number)
      .map((value) => value - 1); //3차원 [4][N][M]맵 ('O' : 이동 가능, '#' : 벽 또는 유령, '*' : 유령의 visible)
  } else {
    for (let m = 0; m < M; m++) {
      if (line[m] !== ".") {
        Map.map((tempMap) => {
          tempMap[Ntemp][m] = "#";
        });
        if (line[m] !== "#") {
          GhostList.push([Ntemp * M + m, Number(line[m])]);
        }
      }
    }
    Ntemp++;
  }
};

const isIn = (x, y) => {
  return x >= 0 && x < N && y >= 0 && y < M;
};

const setGhostOnMap = () => {
  //유령의 회전별 visible (이동 불가)지역 구하기
  GhostList.map((gInfo) => {
    for (let addTime = 0; addTime < 4; addTime++) {
      //0~3 회전별 유령의 visible 맵맵
      const gDir = (gInfo[1] + addTime) % 4; //회전한 유령의 방향
      let gX = Math.floor(gInfo[0] / M); //유령의 위치 X
      let gY = gInfo[0] % M; //유령의 위치 Y
      while (true) {
        gX += Deltas[gDir][0];
        gY += Deltas[gDir][1];
        if (!isIn(gX, gY) || Map[addTime][gX][gY] === "#") {
          //범위 밖이거나 벽/유령으로 막힘
          break;
        }
        Map[addTime][gX][gY] = "*";
      }
    }
  });
};

//해당 위치에서 3초 기다리는 것도 가능
const setMoveSJ = () => {
  //석준이 움직이기 (BFS)
  const moveQ = [[Sx, Sy, 0]]; //시작 지점
  const visMap = Array.from(new Array(4), () =>
    Array.from(new Array(N), () => new Array(M).fill(false))
  ); //석준이 timing별 방문 맵
  while (moveQ.length > 0) {
    const [cX, cY, time] = moveQ.shift();
    const nextTime = time + 1;
    for (let dir = 0; dir < 5; dir++) {
      //석준이 다음 이동 방향별
      const nX = cX + Deltas[dir][0]; //이동 위치
      const nY = cY + Deltas[dir][1];
      if (!isIn(nX, nY) || Map[nextTime % 4][nX][nY] !== "O") {
        //범위 밖 // 이동 불가
        continue;
      }
      //해당 타이밍에 nX, nY로 이동 가능
      if (visMap[nextTime % 4][nX][nY]) continue; //해당 시점에 해당 위치에 이미 왔음
      visMap[nextTime % 4][nX][nY] = true; //해당 시점에 해당 위치 방문체크
      if (nX === Ex && nY === Ey) {
        //탈출성공
        return nextTime;
      }
      moveQ.push([nX, nY, nextTime]); //위치 BFS 기록
    }
  }
  return "GG";
};

const getResult = () => {
  setGhostOnMap(); //귀신이 timing%4의 타이밍에 보는 (이동 불가)지역 구하기
  return setMoveSJ(); //석준이 움직이기 (return : 0~ or 'GG')
};

const solv = () => {
  console.log(getResult());
};

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit();
  });