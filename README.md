## Информация
Это back-end приложение, которое имеет RESTful HTTP методы для магазина, торгующего компьютерами и комплектующими

## Сборка приложения

Клонирование репозитория

Сначала клонируйте репозиторий с исходным кодом приложения:

```
git clone <URL_REPOSITORY>
cd <PROJECT_DIRECTORY>
```

## Сборка с Maven

Перейдите в корневую директорию проекта и выполните команду для сборки:
```
mvn clean install
```

Эта команда загрузит все необходимые зависимости и соберет проект.

После сборки проекта с помощью Maven, запустите приложение командой:

```
java -jar target/test-0.0.1-SNAPSHOT.jar
```

## Использование приложения

1) Добавление товара
```
   POST localhost:8080/api/v1/products/add
```
```
{
  "serialNumber": "35334",
  "manufacturer": "HP",
  "price": 950.0,
  "quantity": 11,
  "productType": "DESKTOP",
  "formFactor": "factor",
  "type": "DESKTOP"
}
```
2) Редактирование товара
```
   PATCH localhost:8080/api/v1/products/edit/1
```
```
  "serialNumber": "3512334",
  "manufacturer": "HP",
  "price": 1150.0,
  "quantity": 10,
  "productType": "DESKTOP",
  "formFactor": "factor",
  "type": "DESKTOP"
}
```

3) Просмотр всех существующих товаров по типу
```
   GET localhost:8080/api/v1/products/type?type=DESKTOP
```

4) Просмотр товара по идентификатору.
```
   GET localhost:8080/api/v1/products/1
```