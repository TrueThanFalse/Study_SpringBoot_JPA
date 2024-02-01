console.log('boardDetail.js Join Success');

document.getElementById('listBtn').addEventListener('click', ()=>{
   location.href = "/board/list"; 
});

document.getElementById('delBtn').addEventListener('click', ()=>{
   document.getElementById('delForm').submit();
});

document.getElementById('modBtn').addEventListener('click', ()=>{
   // modify.html을 만들지 않고 detail.html을 수정하여
   // modify.html처럼 만드는 방법
   document.getElementById('title').readOnly = false;
   document.getElementById('content').readOnly = false;
   
   document.getElementById('modBtn').remove();
   document.getElementById('delBtn').remove();

   // 새로운 버튼 생성
   let editBtn = document.createElement('button');
   editBtn.classList.add('btn', 'btn-success');
   editBtn.setAttribute('type', 'submit');
   editBtn.innerText = "Edit";

   // 새로 생성한 버튼(editBtn)을 id 위치에 넣어주기
   document.getElementById('DivBtn').appendChild(editBtn);
})