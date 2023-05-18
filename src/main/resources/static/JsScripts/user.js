const url = "/api/user";

fetch(url).then(response => response.json())
    .then(data => {
        let roles = "";
        data.roles.forEach(role => {
            roles += role.role.replace("ROLE_","") + " ";
        })

        let body = `
        <tr class="table">
        <th scope="row">${data.id}</th>
        <td>${data.username}</td>
        <td>${data.surname}</td>
        <td>${data.age}</td>
        <td>${data.email}</td>
        <td>${roles}</td>
        </tr>
    `
        document.querySelector('#table_data').innerHTML = body;
    })