layui.use(["element", "jquery", "table", "layer", "form", "laydate", "authUtils",], function () {
    let authUtils = layui.authUtils, Action = layui.authUtils.Action, $ = layui.jquery;
    setOptionValues($('#roles'));

    function setOptionValues(roles) {
        //查询权限并赋值
        authUtils.axGet(Action.ROLE_LISTS_URL, null, function (result) {
            if (result.flag) {
                for (const key in result.data) {
                    roles.append('<option value="' + result.data[key].id + '">' + result.data[key].id + " -- " + result.data[key].roleName + '</option>');
                }
            } else {
                authUtils.redCryMsg(result.message);
            }
        });

        setTimeout(resetForm(), 2000)
    }

    function getUserInfoById(dataForm, userId) {
        if (userId !== undefined || userId !== "") {
            authUtils.axGet(Action.USERS_SEL_URL, {id: userId}, function (result) {
                if (result.flag) {
                    setValues(dataForm, result.data);
                } else {
                    authUtils.redCryMsg(result.message);
                }
            });
        }
    }

    function setValues(dataForm, data) {
        for (const key in data) {
            if ('status' === key) {
                let statusValue = data[key] == 0 ? "待审核" : data[key] == 1 ? "已审核" : "已冻结";
                dataForm.find('input[name=' + key + 'es]').val(statusValue);
                dataForm.find('input[name=' + key + '][value=' + data[key] + ']').prop("checked", "checked");
            } else if ('roleId' === key) {
                authUtils.axGet(Action.ROLE_SEL_URL, {id: data[key]}, function (result) {
                    dataForm.find('input[name=' + key + 'es]').val(result.data.id + " -- " + result.data.roleName);
                    dataForm.find('select option[name=' + key + '][value=' + data[key] + ']').prop("selected", "selected");
                });
            } else if ('sex' === key) {
                let sexValues = data[key] === 0 ? "男" : data[key] === 1 ? "女" : "未知";
                dataForm.find('input[name=' + key + 'es]').val(sexValues);
                dataForm.find('input[name=' + key + ']').val(data[key]).prop("checked", "checked");
            } else {
                dataForm.find('input[name=' + key + ']').val(data[key]);
            }
        }
    }

    function setAndRest(dataForm) {
        let id = dataForm.find('input[name="id"]').val();
        dataForm[0].reset();

        getUserInfoById(dataForm, id);
    }

    function resetForm() {
        if ($('#addUserForm')[0] !== undefined) {
            $('#addUserForm')[0].reset()
        }
        let dataInfoForm = $('#dataInfoForm');
        if (dataInfoForm[0] !== undefined) {
            setTimeout(setAndRest(dataInfoForm), 1000)
        }
        let dataEditForm = $('#dataEditForm');
        if (dataEditForm[0] !== undefined) {
            setTimeout(setAndRest(dataEditForm), 1000)
        }
    }


})