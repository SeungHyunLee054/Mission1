function deleteHistory(id){
    if (confirm("삭제하시겠습니까?")){
        location.href="./delete-history?id="+id;
    }
}

function bookMarkAddSubmit(distance){
    alert("북마크 정보를 추가하였습니다.");
    location.href="./bookmark-list?distance="+distance;
}

function bookMarkDeleteSubmit(){
    alert("북마크 정보를 삭제하였습니다.");
    location.href="./bookmark-list";
}

function bookMarkGroupAddSubmit(){
    alert("북마크 그룹 정보를 추가하였습니다.")
    location.href="./bookmark-group";
}

function bookMarkGroupDeleteSubmit(){
    alert("북마크 그룹 정보를 삭제하였습니다.");
    location.href="./bookmark-group";
}

function bookMarkGroupEditSubmit(){
    alert("북마크 그룹 정보를 수정하였습니다.");
    location.href="./bookmark-group";
}