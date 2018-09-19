$(function(){
  $('#zipcode-btn').on('click', function() {
    // 入力された郵便番号でWebAPIに住所情報をリクエスト
    $.ajax({
      url: 'http://zipcloud.ibsnet.co.jp/api/search?zipcode=' + $('#zipcode').val(),
      dataType : 'jsonp',
    }).done(function(data) {
      if (data.results) {
        setData(data.results[0]);
      } else {
        alert('郵便番号が間違っている可能性があります。もう一度お試しください。');
      }
    }).fail(function(data) {
      alert('通信に失敗しました');
    });
  });

  // データ取得が成功したときの処理
  function setData(data) {
    //取得したデータを各HTML要素に代入
    $('#address').val(data.address1 + data.address2 + data.address3);
  }
});
