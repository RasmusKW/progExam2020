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
            let selectElement = document.getElementById("updateId")
            let optionElement = document.createElement("option")


            optionElement.id = "updateStudentId" + data.studentId
            optionElement.innerHTML = "New Student: " + data.studentName
            optionElement.value = data.studentId
            selectElement.appendChild(optionElement)

            let deleteSelectElement = document.getElementById("deleteId")
            let deleteOptionElement = document.createElement("option")


            deleteOptionElement.id = "deleteStudentId" + data.studentId
            deleteOptionElement.innerHTML = "New Student: " + data.studentName
            deleteOptionElement.value = data.studentId
            deleteSelectElement.appendChild(deleteOptionElement)

            let tableBodyElement = document.getElementById("tableBody")
            let tableRowElement = document.createElement("TR")

            tableRowElement.id = "myRowId" + data.studentId

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
            let optionElement = document.getElementById("updateStudentId" + studentId)
            optionElement.innerHTML = data.studentName
            rowElement.children[0].innerHTML = data.studentName
            rowElement.children[1].innerHTML = data.studentEmail
            rowElement.children[2].innerHTML = data.supervisor.name

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
            let rowElement = document.getElementById("myRowId" + studentId)
            let optionElement = document.getElementById("deleteStudentId" + studentId)
            let optionElement2 = document.getElementById("updateStudentId" + studentId)
            rowElement.remove()
            optionElement.remove()
            optionElement2.remove()
            console.log(data)
        },

        error:function(data) {
            console.log(data)
        }
    })



}