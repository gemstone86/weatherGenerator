# Weather Generator For Eon
This program is a weather generator for the game Eon. As a baseline, it uses the world of Mundana, but it can easiily be extended by other worlds if you add other datafiles.

## Temperature Wind, Precipation, and other events
the generator dynamically generates the temperature, wind conditions, the precipation, and other events for the area selected. The Each attribute is procedurally generated, so the weather stays the same on each specific date (although it differs between versions of the weather generator).

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