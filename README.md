# ScenarioMix

Версия: 1.1.0\
Spigot-плагин на сценарии для ивентов\
Нативная версия: 1.16.5\
ПРОВЕРЕНО НА 1.12.2 - 1.16.5

## Использование

Просто напишите команду ``/mix``, и выберите необходимый сценарий.\
Для настройки сценария нажмите ПКМ по сценарию

## Разрешения

- **scenariomix.menu** - открывать меню со сценариями
- **scenariomix.tools** - использовать инструменты с плагина (например палку с Выброса TNT)
- **scenariomix.debug** - Показывать сообщения отладки

## Доступные сценарии
### Игровые сценарии

- **Толкатель** - Каждую секунду толкает всех игроков в случайную сторону
- **Низшему не жить** - Каждую минуту самый нижний игрок умирает
- **Снежки** - Теперь снежки могут наносить урон
- **Снегопад** - Запускает сильный снегопад
- **Последний взгляд** - Взгляд на человека с тегом ``last_sight`` вас мгновенно убьёт. Чтобы добавить тег введите ``/scoreboard players tag <nick> add last_sight``
- **Заполнение** - Постепенно заполняет мир выбранным блоком
- **Паукопакалипсис** - Дождь из пауков, аллилуйя
- **Апокалипсис** - Запускает метеоритный дождь
- **Погнали** - Теперь можно ездить на игроках
- **Тактильная смерть** - Обнимашки запрещены
- **Не прыгать** - Прыгать нельзя. Я серьёзно, не прыгай
- **Security** - Игрок с тегом ``weak`` не может бить, с тегом ``strong`` не может ломать
- **Рандомная выдача предметов** - Выдает определенное количество рандомных блоков за определенное время и из определенного списка (по умолчанию все блоки и предметы)
- **Выброс TNT** - Возможность выбрасывать TNT определенным предметом. Задача игроков: не попасть в воду.

### Инструменты

- **Авто-гм 3** - Когда игрок возрождается, он автоматически получает гм 3
- **Авто-возрождение** - При смерти игрок будет автоматически возрождён
- **Все на меня!** - Все игроки могут бить только тех, у кого есть тег fight_me.  Чтобы добавить тег введите ``/scoreboard players tag <nick> add fight_me``
- **Ограничение высоты** - Устанавливает максимальную и минимальную высоту установки блоков 
- **Рандомные команды** - Рандомно делит игроков на команды в scoreboard 