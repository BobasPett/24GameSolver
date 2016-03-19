import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solve24Game {


    public static ArrayList<Integer> makelist(){
        ArrayList al = new ArrayList();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 4 numbers each separated by a line (press enter after each number)");

        for(int j = 0; j<4; j++){
            int input = sc.nextInt();
            al.add(input);
        }
        return al;
    }

    public static void combos(ArrayList ops,ArrayList al,int k, int choose,List<List<Integer>> nerd1,List<List<String>> nerd2){
        int sum=0;
        for(int i=0;i<al.size();i++){
            if(al.get(i).equals(1)){
                sum+=1;
            }
        }
        if(sum!=choose && k<al.size()){
            al.set(k,1);
            combos(ops,al,k+1,choose, nerd1,nerd2);
            al.set(k,0);
            combos(ops,al,k+1,choose,nerd1,nerd2);
        }

        if(sum==choose){
            printcombos(al,ops,nerd1,nerd2);
        }

    }

    public static void printcombos(ArrayList al,ArrayList al0,List<List<Integer>> nerd1,List<List<String>> nerd2) {
        ArrayList al1 = new ArrayList();

        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).equals(1)) {
                al1.add(al0.get(i));

            }
        }
        boolean d = true;//if combo not in list -->true
        int x1 = nerd2.size();


        if (x1 >= 1) {// java.lang.OutOfMemoryError: Java heap space
            for(int i=0;i < x1; i++){
                if(nerd2.get(i).equals(al1)){
                    d=false;
                }
                else{
                    d=true;
                }
            }
            if(d){
                nerd2.add(al1);

            }
        }



        else {
            nerd2.add(al1);
            d = true;
        }


        List<List<Integer>> fme = new ArrayList<List<Integer>>();
        List<List<String>> q = new ArrayList<List<String>>();
        for (List<Integer> x : nerd1) {
            fme.add(x);
        }

        if(d){
            for (List<Integer> x : fme) {

                List<Integer> temp0 = new ArrayList();
                for (int j = 0; j < x.size(); j++) {
                    int r = x.get(j);
                    temp0.add(r);
                }


                ArrayList temp = new ArrayList();
                for (int j = 0; j < al1.size(); j++) {
                    String g = (String) al1.get(j);
                    temp.add(g);
                }


                List<Integer> v = result0(temp0, temp);


                if (v.get(0).equals(24)) {
                    
                    System.out.println("(" + "(" + x.get(0) + al1.get(0) + x.get(1) + ")" + al1.get(1) + x.get(2) + ")" + al1.get(2) + x.get(3));
                }


            }
        }
    }



    //list1-->numberlist list2-->combos
    public static  List<Integer> result0 (List<Integer> list1, List<String> list2 ){
        if(list1.size()==1){
            return list1;
        }
        else {


            if (list2.get(0) == "+") {
                int e = list1.get(0) + list1.get(1);
                list1.remove(0);
                list1.remove(0);
                list2.remove(0);
                List<Integer> result = new ArrayList<Integer>();
                result.add(e);
                for (int j = 0; j < list1.size(); j++) {
                    result.add(list1.get(j));
                }
                return result0(result, list2);
            }
            if (list2.get(0) == "-") {
                int e = list1.get(0) - list1.get(1);
                list1.remove(0);
                list1.remove(0);
                list2.remove(0);
                List<Integer> result = new ArrayList<Integer>();
                result.add(e);
                for (int j = 0; j < list1.size(); j++) {
                    result.add(list1.get(j));
                }
                return result0(result, list2);

            }
            if (list2.get(0) == "/") {
                int e =( list1.get(0) )/ (list1.get(1));
                list1.remove(0);
                list1.remove(0);
                list2.remove(0);
                List<Integer> result = new ArrayList<Integer>();
                result.add(e);
                for (int j = 0; j < list1.size(); j++) {
                    result.add(list1.get(j));
                }
                return result0(result, list2);
            }
            if (list2.get(0) == "*") {
                int e = list1.get(0) * list1.get(1);
                list1.remove(0);
                list1.remove(0);
                list2.remove(0);
                List<Integer> result = new ArrayList<Integer>();
                result.add(e);
                for (int j = 0; j < list1.size(); j++) {
                    result.add(list1.get(j));
                }
                return result0(result, list2);
            }
        }


        return list1;

    }
    public static  List<List<Integer>> numberlist(List<Integer> al ) {
        if(al.size()==1){
            List<List<Integer>> alx=new ArrayList<List<Integer>>();
            alx.add(al);
            return alx;
        }
        int e=al.get(0);
        al.remove(0);

        List<List<Integer>> list=numberlist(al);
        for(List<Integer> i :list){
            ArrayList alx = new ArrayList();
            for(int j = 0; j < i.size(); j++){
                alx.add(i.get(j));
            }
            list.set(list.indexOf(i),alx);
        } //copy each list in list and repaste them into list

        for(List<Integer> i :list) i.add(e);
        List<List<Integer>> returnlist=new ArrayList<List<Integer>>();

        int i=0;
        for(List<Integer> p:list){
            int k=p.size()-1;

            for (int j = p.size()-1; j > 0; j--) {
                if(j ==p.size()-1){
                    List<Integer> templist= new ArrayList<Integer>();
                    for(Integer x:p) {
                        int tempint = x;
                        templist.add(tempint);
                    }
                    returnlist.add(templist);
                }
                int temp = list.get(i).get(k);

                list.get(i).set(k, list.get(i).get(k - 1)); //swap pos.

                list.get(i).set(k - 1, temp);

                List<Integer> templist= new ArrayList<Integer>();
                for(Integer x:p) {
                    int tempint = x;
                    templist.add(tempint);
                }
                returnlist.add(templist);

                k--;
            }
            i++;
        }
        return returnlist;
    }

    public static void main(String args[]) {
        ArrayList al = new ArrayList();
        ArrayList al0 = new ArrayList();
        List<String> al1 = new ArrayList<String>();

        List<List<String>> al2=new ArrayList<List<String>>();
        List<String> al3 = new ArrayList<String>();



        for (int i = 0; i < 4; i++) {
            for (int j =0; j < 4; j++) {
                al.add(0);
            }
            al0.add("+");
            al0.add("-");
            al0.add("/");
            al0.add("*");
        }


        ArrayList numbers=makelist();

        al1.add("+");
        al1.add("+");
        al1.add("*");




        List<List<Integer>> nerd1=numberlist(numbers);
        combos(al0, al, 0, 3, nerd1,al2);




    }
}
