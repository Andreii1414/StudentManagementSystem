        window.onload = function() {
                    fetch('/isAdmin')
                        .then(response => response.text())
                        .then(data => {
                            if (data === 'admin') {
                                document.querySelector('.actions-admin').style.display = 'block';
                            } else if (data === 'non-admin') {
                                document.querySelector('.admin-message').style.display = 'block';
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                };

        document.getElementById('action1').addEventListener('click', function() {
            fetch('/top10')
                .then(response => response.json())
                .then(data => {
                    let tableContent = `
                                    <h1>Top 10 Elevi</h1>
                                    <table border="1" style="width: 100%; border-collapse: collapse;">
                                        <thead>
                                            <tr>
                                                <th>Nume</th>
                                                <th>Prenume</th>
                                                <th>Clasa</th>
                                                <th>Media</th>
                                            </tr>
                                        </thead>
                                        <tbody>`;
                    data.forEach(item => {
                        const [nume, prenume, media, clasa] = item.split(" ");
                        tableContent += `
                                        <tr>
                                            <td>${nume}</td>
                                            <td>${prenume}</td>
                                            <td>${clasa}</td>
                                            <td>${media}</td>
                                        </tr>
                                    `;
                    });

                    tableContent += `
                                        </tbody>
                                    </table>
                                `;

                    const popup = window.open("", "popup", "width=600,height=500");
                    popup.document.write(`
                                    <html>
                                    <head>
                                        <title>Top 10 Elevi</title>
                                        <style>
                                            table {
                                                width: 100%;
                                                border-collapse: collapse;
                                            }
                                            th, td {
                                                padding: 8px;
                                                text-align: left;
                                                border: 1px solid black;
                                            }
                                            th {
                                                background-color: #f2f2f2;
                                            }
                                            h1 {text-align: center;}
                                        </style>
                                    </head>
                                    <body>
                                        ${tableContent}
                                    </body>
                                    </html>
                                `);
                })
                .catch(error => {
                    console.error('Eroare:', error);
                });
        });

        document.getElementById('action2').addEventListener('click', function() {
            fetch('/diriginti')
                .then(response => response.json())
                .then(data => {
                    let tableContent = `
                                    <h1>Diriginti</h1>
                                    <table border="1" style="width: 100%; border-collapse: collapse;">
                                        <thead>
                                            <tr>
                                                <th>Nume</th>
                                                <th>Prenume</th>
                                                <th>Materie predata</th>
                                                <th>Clasa</th>
                                                <Th>Numar Elevi</th>
                                                <th>Specializare</th>
                                                <th>Extra</th>
                                            </tr>
                                        </thead>
                                        <tbody>`;
                    data.forEach(item => {
                        const [nume, prenume, nume_materie, nume_clasa, nr_elevi, specializare, extra] = item.split(";");
                        const extraFinal = (extra === "null") ? "-" : extra;
                        tableContent += `
                                        <tr>
                                            <td>${nume}</td>
                                            <td>${prenume}</td>
                                            <td>${nume_materie}</td>
                                            <td>${nume_clasa}</td>
                                            <td>${nr_elevi}</td>
                                            <td>${specializare}</td>
                                            <td>${extraFinal}</td>
                                        </tr>
                                    `;
                    });

                    tableContent += `
                                        </tbody>
                                    </table>
                                `;

                    const popup = window.open("", "popup", "width=700,height=500");
                    popup.document.write(`
                                    <html>
                                    <head>
                                        <title>Diriginti</title>
                                        <style>
                                            table {
                                                width: 100%;
                                                border-collapse: collapse;
                                            }
                                            th, td {
                                                padding: 8px;
                                                text-align: left;
                                                border: 1px solid black;
                                            }
                                            th {
                                                background-color: #f2f2f2;
                                            }
                                            h1 {text-align: center;}
                                        </style>
                                    </head>
                                    <body>
                                        ${tableContent}
                                    </body>
                                    </html>
                                `);
                })
                .catch(error => {
                    console.error('Eroare:', error);
                });
        });

                document.getElementById('action3').addEventListener('click', function() {
                    fetch('/top5')
                        .then(response => response.json())
                        .then(data => {
                            let tableContent = `
                                            <h1>Top 5 materii (numar absente)</h1>
                                            <table border="1" style="width: 100%; border-collapse: collapse;">
                                                <thead>
                                                    <tr>
                                                        <th>Nume</th>
                                                        <th>Numar absente</th>
                                                    </tr>
                                                </thead>
                                                <tbody>`;
                            data.forEach(item => {
                                const [nume, nr_absente] = item.split(";");
                                tableContent += `
                                                <tr>
                                                    <td>${nume}</td>
                                                    <td>${nr_absente}</td>
                                                </tr>
                                            `;
                            });

                            tableContent += `
                                                </tbody>
                                            </table>
                                        `;

                            const popup = window.open("", "popup", "width=400,height=350");
                            popup.document.write(`
                                            <html>
                                            <head>
                                                <title>Top 5 materii (numar absente)</title>
                                                <style>
                                                    table {
                                                        width: 100%;
                                                        border-collapse: collapse;
                                                    }
                                                    th, td {
                                                        padding: 8px;
                                                        text-align: left;
                                                        border: 1px solid black;
                                                    }
                                                    th {
                                                        background-color: #f2f2f2;
                                                    }
                                                    h1 {text-align: center;}
                                                </style>
                                            </head>
                                            <body>
                                                ${tableContent}
                                            </body>
                                            </html>
                                        `);
                        })
                        .catch(error => {
                            console.error('Eroare:', error);
                        });
                });

                document.getElementById('action4').addEventListener('click', function() {
                    fetch('/burse')
                        .then(response => response.json())
                        .then(data => {
                            let tableContent = `
                                            <h1>Burse sociale+merit semestrul 2</h1>
                                            <table border="1" style="width: 100%; border-collapse: collapse;">
                                                <thead>
                                                    <tr>
                                                        <th>Nume</th>
                                                        <th>Prenume</th>
                                                        <th>Clasa</th>
                                                    </tr>
                                                </thead>
                                                <tbody>`;
                            data.forEach(item => {
                                const [nume, prenume, nume_clasa] = item.split(" ");
                                tableContent += `
                                                <tr>
                                                    <td>${nume}</td>
                                                    <td>${prenume}</td>
                                                    <td>${nume_clasa}</td>
                                                </tr>
                                            `;
                            });

                            tableContent += `
                                                </tbody>
                                            </table>
                                        `;

                            const popup = window.open("", "popup", "width=400,height=600");
                            popup.document.write(`
                                            <html>
                                            <head>
                                                <title>Burse sociale+merit semestrul 2</title>
                                                <style>
                                                    table {
                                                        width: 100%;
                                                        border-collapse: collapse;
                                                    }
                                                    th, td {
                                                        padding: 8px;
                                                        text-align: left;
                                                        border: 1px solid black;
                                                    }
                                                    th {
                                                        background-color: #f2f2f2;
                                                    }
                                                    h1 {text-align: center;}
                                                </style>
                                            </head>
                                            <body>
                                                ${tableContent}
                                            </body>
                                            </html>
                                        `);
                        })
                        .catch(error => {
                            console.error('Eroare:', error);
                        });
                });