/** Author: Samuel Kapusta
 * Email: skapusta@ucsd.edu
 * Date: May 1, 2019
 *
 * Contains ImageEditor which implements basic functionality of image editor
 * for a gray-scale image that is represented by a 2D array of integers where
 * each pixel is represented by a i, j index.
 **/

/**The ImageEditor's most important functions are blur, sharpen, and delete
 * which allow for manipulation of an individual pixel or it's complete removal.
 * Additionally, the undo function allows for the removal of the last change to
 * the image, and the redo function allows for the removal of the last
 * undo function. Both the undo and redo functions have their own Stacks which
 * store previous versions of 2D image.*/
import java.util.Scanner;
import java.util.ArrayList;

public class ImageEditor {

    int[][] mat;
    Stack undoer;
    Stack redoer;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list1 = new ArrayList(){};
        System.out.println("Enter number of elements");
        int n = scan.nextInt();
        System.out.println("Enter " + n + " integers: ");
        for(int i = 0; i < n; i++){
            list1.add(scan.nextInt());
        }
        for(int i = 0; i < list1.size() - 1; i++){
            for(int j = 0; j < list1.size() - i - 1; j++){
                if(list1.get(j) > list1.get(j+1)){
                    int swap = list1.get(j);
                    list1.set(j, list1.get(j + 1));
                    list1.set(j + 1, swap);
                }
            }
        }
        System.out.println("Sorted list in ascending order: ");
        for(int i = 0; i < list1.size(); i++){
            System.out.println(list1.get(i));
        }

    }

    /**
     * Words
     * @param mat Hi*/
    public ImageEditor(int[][] mat) {
        this.mat = mat;
        undoer = new Stack(10, (float).6, (float).4 );
        redoer = new Stack(10, (float).6, (float).4 );
    }

    /**
     *  Removes pixel at index i,j 2d image represented by int array. Throws
     * error if i or j are out of bounds of the array.
     * @param: i, j values represent location of pixel to remove.
     * */
    void delete(int i, int j) throws IndexOutOfBoundsException{
        if(i > mat.length || j > mat[0].length){
            throw new IndexOutOfBoundsException();
        }
       int[][] mat2 = new int[mat.length][mat[0].length];
       for(int k=0; k<mat.length; k++){
           for(int l =0; l<mat[0].length; l++){
               if(!(k==i && l==j)){
                   mat2[k][l] = mat[k][l];
               }

           }
       }
        undoer.push(mat);
       redoer.clear();
        mat = mat2;
    }

    /**
     * Blurs pixel in grayscale image at i,j by the specified blurFactor.
     * BlurFactor must be under 1, otherwise an IllegalArgumentException is
     * thrown. Throws IndexOutOfBounds if i or j are out of bounds for the
     * array.
     * @param i
     * @param j
     * @param blurFactor
     * @throws IndexOutOfBoundsException
     * @throws IllegalArgumentException*/
    void blur(int i, int j, float blurFactor) throws IndexOutOfBoundsException,
            IllegalArgumentException{
        if(i > mat.length || j > mat[0].length){
            throw new IndexOutOfBoundsException();
        }
        if((blurFactor < 0) || (blurFactor > 1)){
            throw new IllegalArgumentException();
        }
        undoer.push(mat);
        redoer.clear();
        mat[i][j] = (int) ((float)mat[i][j] * blurFactor);

    }

    /** Sharpens pixel in grayscale image at i,j by the specified sharpenFactor.
     * sharpenFactor must be greater then 1, otherwise an
     * IllegalArgumentException is thrown. Throws IndexOutOfBounds if i or j
     * are out of bounds for the array.
     * @param: i, j values represent location of pixel to blur.
     * @param: sharpenFactor is greather then 1 to multiply pixel by.
     * */
    void sharpen(int i, int j, float sharpenFactor)
            throws IndexOutOfBoundsException, IllegalArgumentException{
        if(i > mat.length || j > mat[0].length){
            throw new IndexOutOfBoundsException();
        }
        if(sharpenFactor < 1){
            throw new IllegalArgumentException();
        }
        undoer.push(mat);
        redoer.clear();
        mat[i][j] = (int) ((float)mat[i][j] * sharpenFactor);
        if(mat[i][j] > 255){
            mat[i][j] = 255;
        }
    }

    /**
     * Undo undoes the last method from delete, sharpen, or blur by replacing
     * the current array by the array that had existed before that action
     * occured.
     * @param Boolean on whether undo was performed successfully.
     * */
    boolean undo(){
        if(undoer.isEmpty()){
            return false;
        }
        else{
            redoer.push(mat);
            mat = (int[][])undoer.pop();
            return true;
        }
    }

    /** Redo undoes the undo function by replacing the current array by the
     * array that had existed before the undo was called. Redo only works if the
     * last function was undo, otherwise it is reset whenever another method is
     * called.
     * @param: Boolean on whether redo was performed successfully.
     * */
    boolean redo(){
       if(redoer.isEmpty()){
           return false;
       }
       else{
           undoer.push(mat);
           mat = (int[][])redoer.pop();
           return true;
        }
    }

    int[][] getImage(){
        return mat;
    }


}
