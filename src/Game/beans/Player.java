package Game.beans;

public class Player {
        //This is a Java bean class which will have private instance variables and
        // public getters and setters...

        private int _playerId;
        private String _firstname;
        private String _lastname;
        private String _address;
        private String _postalcode;
        private String _province;
        private String _phoneNumber;

        //public Getters and Setters


        public int get_playerId() {
            return _playerId;
        }

        public String get_firstname() {
            return _firstname;
        }

        public String get_address() {
            return _address;
        }

        public String get_lastname() {
            return _lastname;
        }

        public String get_phoneNumber() {
            return _phoneNumber;
        }

        public String get_postalcode() {
            return _postalcode;
        }

        public String get_province() {
            return _province;
        }

        public void set_playerId(int _playerId) {
            this._playerId = _playerId;
        }

        public void set_firstname(String _firstname) {
            this._firstname = _firstname;
        }

        public void set_lastname(String _lastname) {
            this._lastname = _lastname;
        }

        public void set_address(String _address) {
            this._address = _address;
        }

        public void set_postalcode(String _postalcode) {
            this._postalcode = _postalcode;
        }

        public void set_province(String _province) {
            this._province = _province;
        }

        public void set_phoneNumber(String _phoneNumber) {
            this._phoneNumber = _phoneNumber;
        }
    }


