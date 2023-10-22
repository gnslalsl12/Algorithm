function solution(maps) {
  let N = maps.length;
  let M = maps[0].length;
  let Map = Array.from({ length: N }, () => Array(M).fill(0));
  let PQ = [];
  let Deltas = [
    [-1, 0],
    [0, 1],
    [1, 0],
    [0, -1]
  ];
  
  for (let n = 0; n < N; n++) {
    for (let m = 0; m < M; m++) {
      const value = maps[n][m];
      Map[n][m] = value === 'X' ? 0 : Number(value);
    }
  }
  
  function solve() {
    setIslDivide();
  }

  function setIslDivide() {
    for (let n = 0; n < N; n++) {
      for (let m = 0; m < M; m++) {
        if (Map[n][m] > 0) {
          getIslBfs(n, m);
        }
      }
    }
  }

  function getIslBfs(n, m) {
    const bfsQ = [];
    bfsQ.push(n * M + m);
    let sum = Map[n][m];
    Map[n][m] = 0;

    while (bfsQ.length !== 0) {
      const loc = bfsQ.shift();
      const locN = Math.floor(loc / M);
      const locM = loc % M;

      for (const dir of Deltas) {
        const nextN = locN + dir[0];
        const nextM = locM + dir[1];
        
        if (!isIn(nextN, nextM) || Map[nextN][nextM] === 0) continue;

        sum += Map[nextN][nextM];
        Map[nextN][nextM] = 0;
        bfsQ.push(nextN * M + nextM);
      }
    }

    PQ.push(sum);
  }

  function isIn(n, m) {
    return n >= 0 && n < N && m >= 0 && m < M;
  }

  // Solve and fill answer
  solve();
  
  let answer = [...PQ];
  if (PQ.length === 0) {
    answer = [-1];
  } else {
    answer.sort((a, b) => a - b);
  }
  
  return answer;
}
