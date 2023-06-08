function deleteHistory(id){
    if (confirm("삭제하시겠습니까?")){
        location.href="./delete-history?id="+id;
    }
}