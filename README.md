# Pályageneráló

## Specifikáció

Házi feladatként olyan pályagenerálót fogok megvalósítani,
amely képes egy kis méretű minta alapján nagyobb méretű pályákat létrehozni.
A minta és a generált pálya is kép lesz.
A bemeneti adatokat (forrás kép elérési útvonala, kimeneti kép elérési útvonala,
kernel mérete, kimeneti kép mérete) parancssorból várja majd a program.
A generálás végén a program el fogja menteni a kész képet egy megadott helyre.

Az ötletet [ebből](https://www.youtube.com/watch?v=TO0Tx3w5abQ) a Youtube videóból merítettem.
A videóban szereplő megoldásnál én egy naivabb, saját megoldással fogok próbálkozni.

## HOWTO

### Hol találhatunk mintákat?

Azért, hogy könnyedén ki lehessen próbálni a programomat, az [images](./images)
mappába beleraktam 3 lehetséges mintát.

### Parancssori argumentumok

Az egyes mintákat a következő parancssori argumentumokkal próbálhatjuk ki (de nem csak ezekkel):

1. [caves_in.png](./images/caves_in.png) minta használatára példa:
    * `in=./images/caves_in.png out=caves_out.png kernel-size=16x16 output-size=1024x1024`
2. [circuit_in.png](./images/circuit_in.png) minta használatára példa:
    * `in=./images/circuit_in.png out=circuit_out.png kernel-size=16x16 output-size=512x512`
3. [road_in.png](./images/road_in.png) minta használatára példa:
    * `in=./images/road_in.png out=road_out.png kernel-size=512x512 output-size=9216x9216`

### Megjegyzés

#### Argumentumok

Az egyes argumentumok a következőket jelentik:

1. `in`
    * a bemeneti minta (kép) helye
2. `out`
    * a kimeneti pálya (kép) helye
3. `kernel-size`
    * ekkora darabokat vág ki a program a bemeneti mintából
4. `output-size`
    * a kimeneti pálya mérete

#### Teljesítmény, korlátok

* Amennyiben a kivágott darabokból nem lehetséges kirakni egy szabályos pályát, akkor kivételre fut a program. (A
  megadott példák esetén ilyennel nem szembesültem.)
* Ha a kimenet mérete túl nagy, akkor stack overflow kivételt dob a program.