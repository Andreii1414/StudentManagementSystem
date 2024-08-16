import requests
from bs4 import BeautifulSoup
import random
from datetime import datetime, timedelta
from unidecode import unidecode

def extract_names(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    names = []
    try:
        for li in soup.find_all('li'):
            a_tag = li.find('a')
            if a_tag:
                names.append(a_tag.get_text(strip=True))
    except Exception as e:
        print(f"Error extracting names: {e}")
    return names

def clean_and_filter_names(names):
    names = [unidecode(name) for name in names]
    names = [name for name in names if len(name.split()) == 1]
    return names[28:-5]

surnameList = ['https://ro.wikipedia.org/wiki/Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti',
               'https://ro.wikipedia.org/w/index.php?title=Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti&pagefrom=Bro%C8%99teanu%0ABro%C8%99teanu#mw-pages',
               'https://ro.wikipedia.org/w/index.php?title=Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti&pagefrom=Costescu#mw-pages',
               'https://ro.wikipedia.org/w/index.php?title=Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti&pagefrom=Gog%C3%AErl%C4%83#mw-pages',
               'https://ro.wikipedia.org/w/index.php?title=Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti&pagefrom=Mihaileanu%0AMih%C4%83ileanu#mw-pages',
               'https://ro.wikipedia.org/w/index.php?title=Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti&pagefrom=Plugaru#mw-pages',
               'https://ro.wikipedia.org/w/index.php?title=Categorie:Nume_de_familie_rom%C3%A2ne%C8%99ti&pagefrom=Timofte%0ATimofte#mw-pages']

surnames = []
for url in surnameList:
    s = extract_names(url)
    s = clean_and_filter_names(s)
    surnames += s
first_names = extract_names('https://ro.wikipedia.org/wiki/List%C4%83_de_prenume_rom%C3%A2ne%C8%99ti')
surnames = clean_and_filter_names(surnames)
first_names = clean_and_filter_names(first_names)

total_records = 450
records_per_year = total_records // 4

def random_date(start_year, end_year):
    start_date = datetime(start_year, 1, 1)
    end_date = datetime(end_year, 12, 31)
    time_between_dates = end_date - start_date
    days_between_dates = time_between_dates.days
    random_number_of_days = random.randrange(days_between_dates)
    random_date = start_date + timedelta(days=random_number_of_days)
    return random_date.strftime('%Y-%m-%d')

def get_class_id(birth_year):
    if birth_year == 2006:
        return [13, 14, 15, 16, 17]
    elif birth_year == 2007:
        return [9, 10, 11, 12]
    elif birth_year == 2008:
        return [5, 6, 7, 8]
    elif birth_year == 2009:
        return [1, 2, 3, 4]
    else:
        return []

insert_statements = []
for birth_year in range(2006, 2010):
    class_ids = get_class_id(birth_year)
    records_per_class = records_per_year // len(class_ids)
    for _ in range(records_per_year):
        name = random.choice(surnames)
        first_name = random.choice(first_names)
        birth_date = random_date(birth_year, birth_year)
        class_id = random.choice(class_ids)
        insert_statements.append(f"INSERT INTO elev (nume, prenume, data_nasterii, id_clasa) VALUES ('{name}', '{first_name}', '{birth_date}', {class_id});")

with open('insert_elev.sql', 'w') as file:
    file.write('\n'.join(insert_statements))

