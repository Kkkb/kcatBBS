var log = console.log.bind(console, new Date().toLocaleString())

var e = function (selector) {
    return document.querySelector(selector)
}

var bindButtonClick = function () {
    var button = e("#id-botton-search")
    button.addEventListener("click", function (event) {

        swal("搜索功能暂未开放", "正在开发中..");

    })
}

var _main = function () {
    bindButtonClick()

}

_main()
