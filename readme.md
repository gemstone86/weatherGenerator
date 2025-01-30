# Weather Generator For Eon
This program is a weather generator for the game Eon. As a baseline, it uses the world of Mundana, but it can easiily be extended by other worlds if you add other datafiles.

## Temperature Wind, Precipation, and other events
the generator dynamically generates the temperature, wind conditions, the precipation, and other events for the area selected. The Each attribute is procedurally generated, so the weather stays the same on each specific date (although it differs between versions of the weather generator).

### Wind
 * 0: Stiltje, spegelblank sjö (max 0.2 m/s)
 * 1: Nästan stiltje. Dyningark an förekomma (0.3 - 0.5 m/s)
 * 2: Lätt Bris (1.6 - 3.3 m/s)
 * 3: God bris (3.4 - 5.4 m/s)
 * 4: Frisk Bris (5.5 - 7.9 m/s)
 * 5: Styv bris (8.0 - 10.7 m/s)
 * 6: Hård Bris (10.8 - 13.8 m/s)
 * 7: Styv kuling (13.9 - 17.1 m/s)
 * 8: Hård Kuling (17.2 - 20.7 m/s)
 * 9: Halv storm (20.8 - 24.4 m/s)
 * 10: Storm (24.5 - 28.4 m/s)
 * 11: Svår storm (28.5 - 32.6 m/s)
 * 12: Lätt Orkan (32.7 - 36.9 m/s)
 * 13: Orkan (37.0 - 41.4 m/s)
 * 14: Svår Orkan (41.5 - 46.1 m/s)
 * 15: (46.2 - 50.9 m/s)
 * 16: (51.0 - 56.0 m/s)
 * 17: (56.1 - 61.2 m/s)

### Precipation (Regn)
* 0: Ingen nederbörd
* 1: enstaka droppar/snöflingor
* 2: Lätt dugg
* 3: dugg
* 4: lätt regn/snöfall

## Events
there are many different events that can take place and some areas have vastly different events attributed to them.

# Usage
To use the program, simply start it and move to the date you want to generate the weather for. The generator does not take "Perpetierna" into account and generates the weather and data for those dates just like any other date.

# Datafiles
The Datafiles are built in the following manner:

(Area name)

(temperature)jan;feb;mar;apr;may;jun;jul;aug;sep;okt;nov;dec;

(precipation)jan;feb;mar;apr;may;jun;jul;aug;sep;okt;nov;dec;

(shift - unused variable for now)

(wind bonus - value to increase wind)

(event name)

(# times per);(x days)