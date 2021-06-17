# testUBRR
Система Кодирования/Декодирования данных
Реализовать консольное приложение позволяюще производить шифрование и дешифрование данных сдвигом символов.
Программа должна поддерживать 2 вида алгоритма:
1) Сдвиг по таблице ASCII(UNICODE);
2) Сдвиг по английскому алфавиту (SHIFT) в случае выхода за границы, переходить на противоположный конец алфавита (z->a), символы отличные от букв не шифровать;

Требуемые аргументы командной строки (Очередность параметров может быть производной):
-mode команда управления, принимает два значения enc/dec, где enc = encryption, dec = decryption
-in путь к файлу с данными для обработки
-out путь к файлу в который нужно сохранить результат (создавать в случае отсутствия)
-key ключ для сдвига (количество позиций на который нужно сдвинуть символ)
-alg тип алгоритма, принимает одно из двух значений unicode/shift
-data данные для шифрования

Если не передан параметр -mode, программа должна работать в режиме шифрования;
Если не передан параметр -key, считаем его нулем;
Если параметр -data не задан, а так же -in отсутствует, то за данные брать пустую строку;  
Если параметр -out не задан, данные выводить в стандартный поток вывода;
Если переданы оба параметра -data и -in, отдавать предпочтение данным из параметра -data;
Если не задан параметр -alg, работаем по алгориту shift

Пример работы алгоритмов:

UNICODE(сдвиг на 5): Lets Encrypt -> Qjyx%Jshw~uy
SHIFT(сдвиг на 7) : Lets Encrypt -> Slaz Lujyfwa

Пример переданных параметров: 
-mode enc -key 5 -data "Lets Encrypt" -alg unicode -in input.txt -out output.txt
