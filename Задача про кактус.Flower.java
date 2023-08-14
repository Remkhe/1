import java.time.LocalDate;
import java.time.Month;

//класс `Flower`, содержит информацию о дате последнего полива и методы для проверки необходимости полива
// в зависимости от времени года и влажности воздуха.
class Flower {
    private LocalDate lastWateringDate; //дата последнего полива

    public Flower(LocalDate lastWateringDate) {
        this.lastWateringDate = lastWateringDate;
    }

    public LocalDate getLastWateringDate() {
        return lastWateringDate;
    }

    public void setLastWateringDate(LocalDate lastWateringDate) {
        this.lastWateringDate = lastWateringDate;
    }

    //Не пора ли полить цветок?
    public boolean needsWatering() {
        System.out.println("Сегодня: " + LocalDate.now());
        System.out.println("Дата последнего полива: " + lastWateringDate);
        // Определение необходимости полива на основе текущего сезона и влажности воздуха
        if (isWinter()) {   //если сейчас зима
            System.out.println("Сейчас зима, поэтому влажность не важна. Поливаем раз в месяц.");
            //Дата следующего полива = дата последнего полива + месяц.
            LocalDate nextWateringDate = lastWateringDate.plusMonths(1);
            //Если сейчас дата позже даты, когда необходимо полить цветок, то его точно надо полить.
            //Если не позже, то не надо его пока поливать.
            return LocalDate.now().isAfter(nextWateringDate);
        } else {
            //Если не зима сейчас, то важна влажность.
            int humidity = Sensor.getHumidity(); //Измерим влажность сенсором.
            System.out.println("Влажность " + humidity + "%");
            if (humidity < 30) { //Если ниже 30%, то поливаем, но не чаще 1 раза в день летом.
                 if (isSummer()) {    //Если лето, то к дате последнего полива прибавляем день и получаем дату следующего полива.
                     System.out.println("Сейчас лето. Влажность ниже 30%. Поливаем не чаще, чем раз в день.");
                     LocalDate nextWateringDate = lastWateringDate.plusDays(1);
                     return LocalDate.now().isAfter(nextWateringDate); //Если сегодня уже позже даты следующего полива, то пора поливать.
                 }
                 else{
                     System.out.println("Весной и осенью поливаем сразу, как только влажность станет <30%. Сейчас она ниже нормы.");
                     return true; //Если не лето, то при влажности <30% поливаем в любом случае.
                 }
            } else if (isSpring() || isAutumn()) { //Если выше 30% и сейчас осень или весна, то смотрим сколько прошло времени с даты последнего полива.
                System.out.println("Весной и осенью при влажности >=30% поливаем раз в неделю.");
                LocalDate nextWateringDate = lastWateringDate.plusWeeks(1); //К дате полива прибавим неделю.
                return LocalDate.now().isAfter(nextWateringDate);   //Если сейчас позже, чем дата полива + неделя, то пора поливать.
            }  else {
                System.out.println("Сейчас лето, и влажность >=30%. Пока поливать не нужно.\n" +
                        "Если влажность станет ниже 30%, то полив будет возможен в дату, указанную ниже.");
            }
        }
        return false; //В остальных случаях поливать пока не надо
    }

    //Если не учитывать влажность воздуха, то следующий плановый полив вычисляется в этом методе.
    //На входе - дата последнего полива.
    //В зависимости от времени года, к этой дате прибавляется месяц, неделя или день, согласно условию.
    public LocalDate calculateNextWateringDate() {
        if (isWinter()) {
            return lastWateringDate.plusMonths(1);
        } else if (isSpring() || isAutumn()) {
            return lastWateringDate.plusWeeks(1);
        } else if (isSummer()) {
            return lastWateringDate.plusDays(1);
        }
        return null;
    }

    //Зима ли сейчас?
    private boolean isWinter() {
        Month currentMonth = LocalDate.now().getMonth();
        return currentMonth.compareTo(Month.DECEMBER) == 0 ||
                currentMonth.compareTo(Month.JANUARY) == 0 ||
                currentMonth.compareTo(Month.FEBRUARY) == 0;
    }

    //Весна сейчас?
    private boolean isSpring() {
        Month currentMonth = LocalDate.now().getMonth();
        return currentMonth.compareTo(Month.MARCH) == 0 ||
                currentMonth.compareTo(Month.APRIL) == 0 ||
                currentMonth.compareTo(Month.MAY) == 0;
    }

    //Лето сейчас?
    private boolean isSummer() {
        Month currentMonth = LocalDate.now().getMonth();
        return currentMonth.compareTo(Month.JUNE) == 0 ||
                currentMonth.compareTo(Month.JULY) == 0 ||
                currentMonth.compareTo(Month.AUGUST) == 0;
    }

    //Осень сейчас?
    private boolean isAutumn() {
        Month currentMonth = LocalDate.now().getMonth();
        return currentMonth.compareTo(Month.SEPTEMBER) == 0 ||
                currentMonth.compareTo(Month.OCTOBER) == 0 ||
                currentMonth.compareTo(Month.NOVEMBER) == 0;
    }
}
