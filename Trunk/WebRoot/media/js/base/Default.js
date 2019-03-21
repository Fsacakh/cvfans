/**
 * 设置AJAX请求缺省值
 */
$.ajaxSetup({
	async:false
});

bootbox.setDefaults({
  /**
   * @optional String
   * @default: en
   * which locale settings to use to translate the three
   * standard button labels: OK, CONFIRM, CANCEL
   */
  locale: "zh_CN",
  
  /**
   * @optional Boolean
   * @default: true
   * whether the dialog should be shown immediately
   */
  show: true,
  
  /**
   * @optional Boolean
   * @default: true
   * whether the dialog should be have a backdrop or not
   */
  backdrop: true,
  
  /**
   * @optional Boolean
   * @default: true
   * show a close button
   */
  closeButton: true,
  
  /**
   * @optional Boolean
   * @default: true
   * animate the dialog in and out (not supported in < IE 10)
   */
  animate: true,
  
  /**
   * @optional String
   * @default: null
   * an additional class to apply to the dialog wrapper
   */
  className: "my-modal"
});

$.extend( true, $.fn.raty.defaults, {
	"hints": ['1', '2', '3', '4', '5'],
	"path":$.URL.get('media/image/rate'), 
	"showHalf":true
});