document.getElementById("action6").onclick = function() {
    var newWindow = window.open("", "_blank", "width=500,height=550");

    newWindow.document.write(`
        <html>
        <head>
            <title>Adaugare Elev</title>
            <link rel="stylesheet" href="/forms.css">
            <style>
                body { font-family: Arial, sans-serif; padding: 20px; background-color: white; }
                button{	background-color: rgb(24, 121, 186);
                       	color: white;
                       	font-size: 12px;
                       	padding: 10px 45px;
                       	border: 1px solid transparent;
                       	border-radius: 8px;
                       	font-weight: 600;
                       	letter-spacing: 0.5px;
                       	text-transform: uppercase;
                       	margin-top: 10px;
                       	cursor: pointer;
                       	align-self: center;}
                label { display: block; margin-top: 10px; }
                form {
                    display: flex;
                    flex-direction: column;
                    width: 100%;
                    max-width: 400px;
                }
                input, select { width: 100%; padding: 5px; margin-top: 5px; }
                #semestru-container { display: none; }
            </style>
        </head>
        <body>
            <h2>Formular Mutare Elev</h2>
            <form>
                <label for="idElev">Id-ul elevului</label>
                <input type="text" id="idElev" name="idElev" required>

                <div id="clasa-container">
                    <label for="clasa">Clasa:</label>
                    <select id="clasa" name="clasa">

                    </select>
                </div>

                <button type="submit">Muta Elev</button>
            </form>

            <script>
            fetch('/clase')
                .then(response => response.json())
                .then(data => {
                    const clasaSelect = document.getElementById("clasa");
                    data.forEach(clasa => {
                        const [id, ...numeParts] = clasa.split(" ");
                        const nume = numeParts.join(" ");
                        const option = document.createElement("option");
                        option.value = id;
                        option.textContent = nume;
                        clasaSelect.appendChild(option);
                    });
                });

                document.querySelector("form").onsubmit = function(event) {
                    event.preventDefault();

                    const formData = new URLSearchParams();
                    formData.append("id_elev", document.getElementById("idElev").value);

                    const clasaValue = document.getElementById("clasa").value;
                    formData.append("clasa", clasaValue);

                    fetch('/mutaElev', {
                        method: 'POST',
                        body: formData
                    })
                    .then(response => response.text())
                    .then(result => {
                        alert(result);
                        window.close();
                    })
                    .catch(error => {
                        console.error('Eroare:', error);
                    });
                };

            </script>
        </body>
        </html>
    `);
};


