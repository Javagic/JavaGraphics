# JavaGraphics
Приложение содержит базовый функцонал для построения и отрисовки геометрических фигур и обладает следующей функциональностью:
* Загрузка 3D моделей из obj файлов
* Построение модели с задаными параметрами освещенности и текстур

В программе реализованы следующие  алгоритмы:
* Алгоритм Брезенхэма
* Алгоритм Ву
* Растеризация треугольников
* Отсечение задних граней модели
* Z-буферизация
* Шейдер Гуро


![culling](https://raw.githubusercontent.com/Javagic/JavaGraphics/master/images/culling_image.jpg)
![luminosity](https://raw.githubusercontent.com/Javagic/JavaGraphics/master/images/luminosity_image.jpg)
![texture](https://raw.githubusercontent.com/Javagic/JavaGraphics/master/images/texture_image.jpg)

## Сборка⚒
### Откройте проект в IDEA
```
git clone https://github.com/Javagic/JavaGraphics.git
```
Откройте директорию `JavaGraphics/` в IDEA.

### Сборка проекта
Для получения прав на запуск сборщика введите 
```shell
$ chmod +x gradlew
```
Следующая команда начнет сборку проекта
```shell
$ ./gradlew build
```
Для сборки с автотестами использовать команду
```shell
$ ./gradlew test build
```
Jar файл `JavaGraphics-1.0-SNAPSHOT.jar` с программой будет находится в директории `build/libs`
Для его запуска необходимо ввести команду 
```shell
$ java build/libs/JavaGraphics-1.0-SNAPSHOT.jar
```
