const solution = (m, times) => {
  times.sort((a, b) => a - b);
  let start = 0;
  let end = times[times.length - 1] * m;

  while (start < end) {
    let mid = Math.floor((start + end) / 2);
    const cnt = times.reduce((ac, cur) => ac + Math.floor(mid / cur), 0);
    if (mid === end) {
      console.log(end);
      return;
    }
    if (cnt >= m) end = mid;
    else start = mid + 1;
  }
  console.log(start);
};

const read = () => {
  let input = require('fs')
    .readFileSync('dev/stdin')
    .toString()
    .trim()
    .split('\n');
  const [n, m] = input.shift().split(' ');
  const arr = input.map((c) => parseInt(c, 10));
  solution(m, arr);
};

read();