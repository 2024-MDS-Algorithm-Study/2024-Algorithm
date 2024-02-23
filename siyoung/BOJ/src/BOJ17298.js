let fs = require('fs');
let [n, input] = fs.readFileSync('./input.txt').toString().trim().split('\n');
// let [n, input] = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
input = input.trim().split(' ').map(Number);

let ret = new Array(input.length).fill(-1);
let st = []

for(let i=0; i<input.length; i++) {
    let cur = input[i];
    while(st.length>0) {
        let prev = st.pop();
        if(cur>input[prev]) ret[prev] = cur;
        else {
            st.push(prev);
            break;
        }
    }
    st.push(i);
    // console.log((st));
}
console.log(ret.join(' '));