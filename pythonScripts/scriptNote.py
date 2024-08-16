import random
from datetime import datetime, timedelta

num_elevi = 448
num_materii = 14

def random_date(start_date, end_date):
    time_between_dates = end_date - start_date
    days_between_dates = time_between_dates.days
    random_number_of_days = random.randrange(days_between_dates)
    random_date = start_date + timedelta(days=random_number_of_days)
    return random_date.strftime('%Y-%m-%d')

start_sem1 = datetime(2023, 9, 10)
end_sem1 = datetime(2024, 2, 1)
start_sem2 = datetime(2024, 2, 2)
end_sem2 = datetime(2024, 6, 10)

def generate_grade():
    grade_probs = [0.1, 0.2, 0.3, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 0.8]  
    grades = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    return random.choices(grades, weights=grade_probs, k=1)[0]

def get_semester(date):
    return 1 if start_sem1 <= date <= end_sem1 else 2

insert_statements = []
num_notes_per_elev = random.randint(15, 20)

for elev_id in range(1, num_elevi + 1):
    for _ in range(num_notes_per_elev):
        materia_id = random.choices(range(1, num_materii + 1), weights=[1]*10 + [0.5]*4)[0]
        grade = generate_grade()
        date_notare = random_date(start_sem1, end_sem2)
        date_notare_obj = datetime.strptime(date_notare, '%Y-%m-%d')
        semestru = get_semester(date_notare_obj)

        insert_statements.append(
            f"INSERT INTO nota (id_elev, id_materie, semestru, valoare, data_notare) "
            f"VALUES ({elev_id}, {materia_id}, {semestru}, {grade}, '{date_notare}');"
        )

random.shuffle(insert_statements)

with open('insert_nota.sql', 'w') as file:
    file.write('\n'.join(insert_statements))

