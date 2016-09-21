/**
 * Created by wuwan on 2016/9/19.
 */
/*使用js应该采用模块化，利于逻辑表达
 * seckill.detail.init(params)
 *
 * */

var seckill = {
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' +
                seckillId + '/' + md5 + '/execution';
        }
    },
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    handleSeckill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开启秒杀
                    var md5 = exposer['md5'];
                    var killURL = seckill.URL.execution(seckillId, md5);
                    console.log('killURL :' + killURL);
                    $('#killBtn').one('click', function () {
                        $(this).addClass('disabled');
                        $.post(killURL, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    //    未开启秒杀
                    var nowTime = exposer['now'];
                    var startTime = exposer['start'];
                    var endTime = exposer['end'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                }
            }
        });
    },

    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            seckillBox.html("秒杀已结束!");
        } else if (nowTime < startTime) {
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    detail: {
        init: function (params) {
            var killPhone = $.cookie('killPhone');
            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log("inputPhone=" + inputPhone);
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label> ').show(300);
                    }
                });
            }
            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                }
            });
        }
    }
}