public class Test {
    public void sort(int[] arr,int l,int r){
        if (l>r){
            return;
        }
        int v = arr[l];
        int i = l+1;
        int lt = l;
        int gt = r+1;
        //arr[l+1 ... lt] < v ; arr[lt+1 ... i) == v ; arr[gt .... r] > v
        while (i<gt){
            if (arr[i] == v){
                i++;
            }else if (arr[i] < v){
                swap(arr,i,lt+1);
                lt++;
                i++;
            }else {
                swap(arr,gt-1,i);
                gt --;
            }
        }
        swap(arr,l,lt);
        sort(arr,l,lt-1);
        sort(arr,gt,r);

    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public void print(int[] arr){
        for (int i = 0;i<arr.length;i++){
            System.out.print(arr[i]);
            if (i < arr.length-1){
                System.out.print(",");
            }
        }
        System.out.println("\n");


    }
    public static void main(String[] args) {
        int[] arr = {9,2,8,1,2,4,3,7,5,3,6};
        Test test = new Test();
        test.print(arr);
        test.sort(arr,0,arr.length-1);
        test.print(arr);
    }
}
