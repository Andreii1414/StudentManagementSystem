document.getElementById("action7").onclick = function() {
    var newWindow = window.open("", "_blank", "width=500,height=550");

    newWindow.document.write(`
        <html>
        <head>
            <title>Motivare absenta</title>
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
            </style>
        </head>
        <body>
            <h2>Formular Motivare Absenta</h2>
            <form>
                <label for="idElev">Id-ul elevului:</label>
                <input type="text" id="idElev" name="idElev" required>

                <label for="materie">Materie:</label>
                <select id="materie" name="materie">

                </select>

                <label for="data_absenta">Data absenta (yyyy-mm-dd):</label>
                <input type="text" id="data_absenta" name="data_absenta" required pattern="\\d{4}-\\d{2}-\\d{2}">

                <div id="semestru-container">
                    <label for="semestru">Semestru:</label>
                    <select id="semestru" name="semestru">
                        <option value="1">1</option>
                        <option value="2">2</option>
                    </select>
                </div>

                <button type="submit">Motiveaza absenta</button>
            </form>

            <script>
            fetch('/materii')
                .then(response => response.json())
                .then(data => {
                    const clasaSelect = document.getElementById("materie");
                    data.forEach(clasa => {
                        const [id, ...numeParts] = clasa.split(";");
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
                    formData.append("data_absenta", document.getElementById("data_absenta").value);

                    const semestruValue = document.getElementById("semestru").value;
                    formData.append("semestru", semestruValue);

                    const clasaValue = document.getElementById("materie").value;
                    formData.append("id_materie", clasaValue);

                    fetch('/motiveazaAbsenta', {
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


