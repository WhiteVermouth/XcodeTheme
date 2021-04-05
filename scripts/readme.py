import os

import markdown
from bs4 import BeautifulSoup


def build_readme(src: str, dest: str):
    if not os.path.exists(dest):
        os.makedirs(dest)
    with open(src, 'r') as source:
        html = markdown.markdown(source.read())
    soup = BeautifulSoup(html, 'html.parser')
    title = soup.find('h1')
    sub_title = title.find_next_sibling('p')
    sub_title['align'] = 'left'
    badges = sub_title.find_next_sibling('p')
    screenshot = badges.find_next_sibling('p')
    screenshot['align'] = 'left'
    badges.decompose()
    title.decompose()
    images = soup.find_all('img')
    for img in images:
        img['width'] = 600
    with open(os.path.join(dest, 'README.html'), 'w') as output:
        output.write(str(soup).strip())
