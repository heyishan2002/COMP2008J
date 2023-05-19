package cards;

public enum Color {
    Brown{
        public int getNeedNum(){
            return 2;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n >= 2) {
                return 2;
            }else {
                return 0;
            }
        }
    },
    LightBlue{
        public int getNeedNum(){
            return 3;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n >= 3) {
                return 3;
            } else {
                return 0;
            }
        }
    },
    Pink{
        public int getNeedNum(){
            return 3;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n >= 3) {
                return 3;
            } else {
                return 0;
            }
        }
    },
    Orange{
        public int getNeedNum(){
            return 3;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n >= 3) {
                return 3;
            } else {
                return 0;
            }
        }
    },
    RED{
        public int getNeedNum(){
            return 3;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n >= 3) {
                return 3;
            } else {
                return 0;
            }
        }
    },
    Yellow{
        public int getNeedNum(){
            return 3;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n >= 3) {
                return 3;
            } else {
                return 0;
            }
        }
    },
    DarkGreen{
        public int getNeedNum(){
            return 3;
        }
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n >= 3) {
                return 3;
            } else {
                return 0;
            }
        }
    },
    DarkBlue{
        public int getNeedNum(){
            return 2;
        }
        public int getRent(int n){
            if(n == 1){
                return 3;
            } else if (n >= 2) {
                return 8;
            }else {
                return 0;
            }
        }
    },
    Black{
        public int getNeedNum(){
            return 4;
        }

        @Override
        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n == 2) {
                return 2;
            } else if (n == 3) {
                return 3;
            } else if (n >= 4) {
                return 4;
            } else {
                return 0;
            }
        }
    },
    LightGreen{
        public int getNeedNum(){
            return 2;
        }

        public int getRent(int n){
            if(n == 1){
                return 1;
            } else if (n >= 2) {
                return 2;
            }else {
                return 0;
            }
        }
    };
    public abstract int getNeedNum();
    public abstract int getRent(int n);
}
