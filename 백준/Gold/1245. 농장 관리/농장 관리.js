let N;
let M;
let Map;
let Vis;
let Result;

function init(line) {
  if (N === undefined) {
    const inputN = line.split(" ").map(Number);
    N = inputN[0];
    M = inputN[1];
    Map = [];
    Vis = Array.from(Array(N), () => Array(M).fill(false));
    Result = 0;
  } else {
    Map.push(line.split(" ").map(Number));
  }
}

function isIn(i, j) {
  return i >= 0 && i < N && j >= 0 && j < M;
}

function setBfs(i, j) {
  const height = Map[i][j];
  let isPeaks = true;
  const que = [];
  que.push([i, j]);
  Vis[i][j] = true;
  while (que.length) {
    const [x, y] = que.shift();
    for ([nextX, nextY] of [
      [x - 1, y],
      [x, y + 1],
      [x + 1, y],
      [x, y - 1],
      [x + 1, y + 1],
      [x + 1, y - 1],
      [x - 1, y + 1],
      [x - 1, y - 1],
    ]) {
      if (!isIn(nextX, nextY)) continue; //범위 밖
      const nextH = Map[nextX][nextY]; //다음 위치의 높이
      if (nextH > height) {
        //시작점보다 높은 높이
        isPeaks = false; //시작점은 봉우리가 아님을 체크
      } else if (nextH === height && !Vis[nextX][nextY]) {
        //시작점과 같은 높이
        Vis[nextX][nextY] = true; //방문 체크
        que.push([nextX, nextY]); //que 추가가
      }
    }
  }
  if (isPeaks) {
    Result++;
  }
}

function getResult() {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (!Vis[i][j]) {
        setBfs(i, j);
      }
    }
  }
}

function solv() {
  getResult();
  console.log(Result);
}

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit();
  });