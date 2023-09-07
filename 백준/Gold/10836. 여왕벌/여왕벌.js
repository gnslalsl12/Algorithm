// 벌집 M*M
//   각 칸에 애벌레 한마리
//   (0, 0) ~ (M-1, M-1)
// 애벌레
//   첫 날은 모두 크기가 1
//   정오에 한번 자람
//      +0, +1, +2 중에
//   N일 동안 반복

let M, N;
let Map;

const main = () => {
    const input = require('fs').readFileSync(process.platform === 'linux' ? '/dev/stdin' : `${__dirname}/input.txt`).toString().trim().split('\n');

    [M, N] = input.shift().split(' ').map(el => +el);

    Map = new Array(M).fill(null).map(() => new Array(M).fill(1));

    input.forEach(row => {
        let grow = row.split(' ').map(el => +el);

        grow[1] += grow[0];
        grow[2] += grow[1];
        let convert = false;
        let index = M-1 - grow[0];
        let add = 1;
        let count = grow[0];
        while(true){
            if(count === grow[1] || count === grow[2]){
                add++;
            }
            if(convert && index == M) break;
            if(index < 0){
                convert = true;
                index *= -1;
            }
            if(!convert){
                Map[index--][0] += add;
            }else{
                Map[0][index++] += add;
            }
            count++;
        }


        // // 입력받은대로 첫열, 첫행 자라게 하고
        // let g = 1;
        // // 첫열
        // let i = (M-1) - grow[0];
        // while(i >= 0) {
        //     if(grow[g] === 0) {
        //         g++;
        //     }

        //     Map[i][0] += g;
        //     grow[g]--;
        //     i--;
        // }
        // // 첫행
        // i = grow[0] <= M ? 1 : 1 + (grow[0] - M);   //
        // while(i < M) {
        //     if(grow[g] === 0) {
        //         g++;
        //     }

        //     Map[0][i] += g;
        //     grow[g]--;
        //     i++;
        // }

        // console.log(Map.map(row => row.map(el => el.size).join(' ')).join('\n'));
        // console.log('--------')
    })

    // 첫열, 첫행 자란거대로 나머지 배열 채우기
    let answer = '';
    for(let r=0; r<M; r++) {
        for(let c=0; c<M; c++) {
            if(r !== 0 && c !== 0) {
                answer += ''+Map[0][c]+' ';
            }else {
                answer += ''+Map[r][c]+' ';
            }
        }
        answer += '\n';
    }

    console.log(answer);
}

main();