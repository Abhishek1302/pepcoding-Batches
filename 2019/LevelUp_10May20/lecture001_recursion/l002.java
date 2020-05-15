public class l002 {
	public static void main(String[] args) {
		solve();
	}

	//CoinChange.==================================================

	public static int coinPermuatation_INF(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++)
		if (tar - arr[i] >= 0) count += coinPermuatation_INF(arr, 0, tar - arr[i], ans + arr[i] + " ");

		return count;
	}

	public static int coinPermuatation_SingleCoin(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++) {
			if (arr[i] > 0 && tar - arr[i] >= 0) {
				int temp = arr[i];
				arr[i] = -arr[i]; // block
				count += coinPermuatation_SingleCoin(arr, 0, tar - temp, ans + temp + " ");
				arr[i] = -arr[i]; // unblock.
			}
		}
		return count;
	}

	public static int coinCombination_INF(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++)
		if (tar - arr[i] >= 0) count += coinCombination_INF(arr, i, tar - arr[i], ans + arr[i] + " ");

		return count;
	}

	public static int coinCombination_SingleCoin(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++)
		if (tar - arr[i] >= 0) count += coinCombination_SingleCoin(arr, i + 1, tar - arr[i], ans + arr[i] + " ");

		return count;
	}

	//================================================================

	public static int coinPermuatation_INF_02(int[] arr, int idx, int tar, String ans) {
		if (tar == 0 || idx == arr.length) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinPermuatation_INF_02(arr, 0, tar - arr[idx], ans + arr[idx] + " ");
		count += coinPermuatation_INF_02(arr, idx + 1, tar, ans);

		return count;
	}

	public static int coinPermuatation_SingleCoin_02(int[] arr, int idx, int tar, String ans) {
		if (tar == 0 || idx == arr.length) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (arr[idx] > 0 && tar - arr[idx] >= 0) {
			int temp = arr[idx];
			arr[idx] = -arr[idx]; // block
			count += coinPermuatation_SingleCoin_02(arr, 0, tar - arr[idx], ans + arr[idx] + " ");
			arr[idx] = -arr[idx]; // unblock
		}
		count += coinPermuatation_SingleCoin_02(arr, idx + 1, tar, ans);

		return count;
	}

	public static int coinCombination_INF_02(int[] arr, int idx, int tar, String ans) {
		if (tar == 0 || idx == arr.length) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinCombination_INF_02(arr, idx, tar - arr[idx], ans + arr[idx] + " ");
		count += coinCombination_INF_02(arr, idx + 1, tar, ans);

		return count;
	}

	public static int coinCombination_SingleCoin_02(int[] arr, int idx, int tar, String ans) {
		if (tar == 0 || idx == arr.length) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinCombination_SingleCoin_02(arr, idx + 1, tar - arr[idx], ans + arr[idx] + " ");
		count += coinCombination_SingleCoin_02(arr, idx + 1, tar, ans);

		return count;
	}

	//WordBreak======================================================
  static String words[] = {"mobile","samsung","sam","sung",  
  "man","mango","icecream","and",  
  "go","i","like","ice","cream","ilike"};

  public static boolean isPresent(String word){
      for(String s: words){
        if(s.equals(word)) return true;
      }
      return false;
  }

  public static int wordBreak(String str,int idx,String ans){
     if(idx==str.length()){
       System.out.println(ans);
       return 1;
     }
    
    int count=0;
    for(int i=idx+1;i<=str.length();i++){
      String nstr=str.substring(idx,i);
      if(isPresent(nstr))
         count+=wordBreak(str,i,ans + nstr + " ");
    }

    return count;
  }

  //crypto.=========================================================
  static String str1="send";
  static String str2="more";
  static String str3="money";
  static boolean[] isNumberTaken=new boolean[10];
  static int[] mapping=new int[26];

  public static int stringToNumber(String str){

    int res=0;
    for(int i=0;i<str.length();i++){
       char ch=str.charAt(i);
       int idx=ch-'a';

       res=res*10+ mapping[idx];
    }
    return res;

  }

  public static int crypto(String str,int idx){
     if(idx==str.length()){
       int a=stringToNumber(str1);
       int b=stringToNumber(str2);
       int c=stringToNumber(str3);
  
       if(a+b==c && mapping[str1.charAt(0)-'a']!=0 && mapping[str2.charAt(0)-'a']!=0 && mapping[str3.charAt(0)-'a']!=0){
         System.out.println(a);
         System.out.println("+" + b + "\n" + "-----" + "\n" + c);
         System.out.println();
         return 1;
       }

       return 0;
     }

     int count=0;
     for(int num=0 ; num < 10 ; num++){
       if(!isNumberTaken[num]){
        
        isNumberTaken[num]=true;
        mapping[str.charAt(idx)-'a']=num;
        
        count+=crypto(str,idx + 1);
        
        isNumberTaken[num]=false;
        mapping[str.charAt(idx)-'a']=0;
       }
     }

     return count;
  }



  public static void crypto(){
    String str = str1 + str2 + str3;

    int[] freq=new int[26];
    for(int i=0;i<str.length();i++) freq[str.charAt(i)-'a']++;

    str="";
    for(int i=0;i<26;i++) if(freq[i] > 0) str+=(char)(i+'a');

    // System.out.println(str);
    System.out.println(crypto(str,0));

  }

  public static void wordBreak(){
    String str="ilikesamsungandmango";
    System.out.println(wordBreak(str,0,""));
  }
  
	public static void coinChange() {
		// int[] arr={2,3,5,7};
		int tar = 10;

		// System.out.println(coinPermuatation_INF(arr, 0, tar, ""));
		// System.out.println(coinPermuatation_SingleCoin(arr, 0, tar, ""));
		// System.out.println(coinCombination_INF(arr, 0, tar, ""));
		// System.out.println(coinCombination_SingleCoin(arr, 0, tar, ""));
	}

	public static void solve() {
    // coinChange();
    // wordBreak();
    crypto();
	}
}