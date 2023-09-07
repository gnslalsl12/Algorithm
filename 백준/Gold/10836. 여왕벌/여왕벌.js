const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
let [M, N] = input.shift().split(' ').map(Number);
Map = new Array(2*M).fill(1);

for(let n = 0; n < N; n++){
    let [zero, one, two] = input[n].split(' ').map(Number);
    let add = 1;
    for(let i = zero; i < 2*M; i++){
        if(i == zero+one) add++;
        Map[i] += add;
    }
}

for(let i = M-1; i >= 0; i--){
    let answer = '' + Map[i];
    for(let j = 0; j < M-1; j++){
        answer += ' ' + Map[M + j];
    }
    console.log(answer);
}