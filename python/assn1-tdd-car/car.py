import time

class Car:
    OFF = 0
    ON = 1
    FULL = 1
    EMPTY = 0
    MAXFUEL = 13
    def __init__(self):
        """car has 4 wheels and standard weight of 1500lbs with 200hp, not like that's important"""
        self._enginestate = self.OFF
        self._speed = 0  # MPH
        self._tankstate = self.EMPTY
        self._fuellevel = 0  # Gallons

    def startEngine(self):
        if self._enginestate == self.OFF:
            self._enginestate = self.ON
        else:
            raise Exception("Engine is already on")

    def stopEngine(self):
        if self._speed == 0 and self._enginestate == self.ON:
            self._enginestate = self.OFF
        else:
            raise Exception("Engine is already off")

    def fillTank(self, amount):
        if amount + self._fuellevel <= self.MAXFUEL:
            self._fuellevel += amount
        else:
            raise Exception("Cant fill tank without overflowing")

    def _engineTick(self):  # 1 tick = 1 RPM
        if self._enginestate == self.ON:
            if self._fuellevel > 0:
                self._fuellevel -= 0.0001
                print("\r Fuel level: {} gal".format(self._fuellevel), end="")
            else:
                self.stopEngine()

    def runUntilEmpty(self, rpm):  # Run engine until tank is gone at rpm
        rps = rpm // 60
        while self._fuellevel > 0:
            self._engineTick()
            time.sleep(1/rps)

if __name__ == "__main__":
    car = Car()
    car.fillTank(13)
    car.startEngine()
    print("Engine Started")
    car.runUntilEmpty(1500000)