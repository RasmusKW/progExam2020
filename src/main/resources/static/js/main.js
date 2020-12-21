function preventFormDefault(form)
{
    form.submit(function(event)
    {
        event.preventDefault();
    })
}

function addStudent()
{
    let studentName = $("#studentName").val();
    let studentEmail = $("#studentEmail").val();
    let supervisorId = $("#supervisorId").val();
    $.ajax({
        url:"/api/addStudent",
        type:"POST",
        contentType:"application/json",
        data: JSON.stringify({
            studentName: studentName,
            studentEmail: studentEmail,
            supervisorId: supervisorId,
        }),
        success:function (data) {
            console.log(data)
            let tableBodyElement = document.getElementById("tableBody")
            let tableRowElement = document.createElement("TR")
            let nameDataElement = document.createElement("TD")
            let emailDataElement = document.createElement("TD")
            let supervisorDataElement = document.createElement("TD")
            nameDataElement.innerHTML = data.studentName
            emailDataElement.innerHTML = data.studentEmail
            supervisorDataElement.innerHTML = data.supervisor.name
            tableRowElement.appendChild(nameDataElement)
            tableRowElement.appendChild(emailDataElement)
            tableRowElement.appendChild(supervisorDataElement)
            tableBodyElement.appendChild(tableRowElement)
        },

        error:function(data) {
            console.log(data)
        }
    })



}



function updateStudent()
{
    let studentId = $("#updateId").val()
    let studentName = $("#updateStudentName").val();
    let studentEmail = $("#updateStudentEmail").val();
    let supervisorId = $("#updateSupervisorId").val();
    $.ajax({
        url:"/api/updateStudent",
        type:"POST",
        contentType:"application/json",
        data: JSON.stringify({
            studentName: studentName,
            studentEmail: studentEmail,
            studentId: studentId,
            supervisorId: supervisorId,
        }),
        success:function (data) {
            console.log(data)
            let rowElement = document.getElementById("myRowId" + studentId)
            let selectElement = document.getElementById("updateStudentId" + studentId)
            selectElement.childNodes[2].innerHTML = data.studentName
            rowElement.childNodes[1].innerHTML = data.studentName
            rowElement.childNodes[3].innerHTML = data.studentEmail
            rowElement.childNodes[5].innerHTML = data.supervisor.name

        },

        error:function(data) {
            console.log(data)
        }
    })

}

function deleteStudent()
{
    let studentId = $("#deleteId").val()
    $.ajax({
        url:"/api/deleteStudent",
        type:"POST",
        contentType:"application/json",
        data: JSON.stringify({
            studentId: studentId,
        }),
        success:function (data) {
            console.log(data)
        },

        error:function(data) {
            console.log(data)
        }
    })



}