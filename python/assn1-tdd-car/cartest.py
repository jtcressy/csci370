from car import Car


def test_startup():
    car = Car()
    car.startEngine()
    assert car._enginestate == Car.ON

def test_shutdown():
    car = Car()
    car.startEngine()
    car.stopEngine()
    assert car._enginestate == Car.OFF

def test_fuelfill():
    car = Car()
    car.fillTank(13)
    assert car._fuellevel == 13

def test_fuelUsage():
    car = Car()
    car.fillTank(13)
    car.startEngine()
    car.runUntilEmpty(15000)
    assert car._fuellevel == 0
