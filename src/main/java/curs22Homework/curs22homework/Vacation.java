package curs22Homework.curs22homework;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Vacation {
        private int id;
        private String location;
        private int price;
        private int duration;

        public Vacation(
                @JsonProperty("location") String location,
                @JsonProperty("price") int price,
                @JsonProperty("duration") int duration) {
            this(0, location, price, duration);
        }

        public Vacation(int id, String location, int price, int duration) {
            this.id = id;
            this.location = location;
            this.price = price;
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public String getLocation() {
            return location;
        }

        public int getPrice() {
            return price;
        }

        public int getDuration() {
            return duration;
        }

        @Override
        public String toString() {
            return "Vacation{" +
                    "id=" + id +
                    ", location='" + location + '\'' +
                    ", price=" + price +
                    ", duration=" + duration +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vacation vacation = (Vacation) o;
            return id == vacation.id &&
                    price == vacation.price &&
                    duration == vacation.duration &&
                    location.equals(vacation.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, location, price, duration);
        }
}
