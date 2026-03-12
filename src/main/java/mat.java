


public class mat {

	

	public static float[][] fft(final float[] inputReal,float[] inputImag,
            boolean DIRECT) {
// - n is the dimension of the problem
// - nu is its logarithm in base e
int n = inputReal.length;

// If n is a power of 2, then ld is an integer (_without_ decimals)
double ld = Math.log(n) / Math.log(2.0);

// Here I check if n is a power of 2. If exist decimals in ld, I quit
// from the function returning null.
if (((int) ld) - ld != 0) {
System.out.println("The number of elements is not a power of 2.");
return null;
}

// Declaration and initialization of the variables
// ld should be an integer, actually, so I don't lose any information in
// the cast
int nu = (int) ld;
int n2 = n / 2;
int nu1 = nu - 1;
float[] xReal = new float[n];
float[] xImag = new float[n];
double tReal, tImag, p, arg, c, s;

// Here I check if I'm going to do the direct transform or the inverse
// transform.
double constant;
if (DIRECT)
constant = -2 * Math.PI;
else
constant = 2 * Math.PI;

// I don't want to overwrite the input arrays, so here I copy them. This
// choice adds \Theta(2n) to the complexity.
for (int i = 0; i < n; i++) {
xReal[i] = inputReal[i];
xImag[i] = inputImag[i];
}

// First phase - calculation
int k = 0;
for (int l = 1; l <= nu; l++) {
while (k < n) {
for (int i = 1; i <= n2; i++) {
 p = bitreverseReference(k >> nu1, nu);
 // direct FFT or inverse FFT
 arg = constant * p / n;
 c = Math.cos(arg);
 s = Math.sin(arg);
 tReal = xReal[k + n2] * c + xImag[k + n2] * s;
 tImag = xImag[k + n2] * c - xReal[k + n2] * s;
 xReal[k + n2] = (float) (xReal[k] - tReal);
 xImag[k + n2] = (float) (xImag[k] - tImag);
 xReal[k] += tReal;
 xImag[k] += tImag;
 k++;
}
k += n2;
}
k = 0;
nu1--;
n2 /= 2;
}

// Second phase - recombination
k = 0;
int r;
while (k < n) {
r = bitreverseReference(k, nu);
if (r > k) {
tReal = xReal[k];
tImag = xImag[k];
xReal[k] = xReal[r];
xImag[k] = xImag[r];
xReal[r] = (float) tReal;
xImag[r] = (float) tImag;
}
k++;
}


float[][] OUTPUT=new float[2][xReal.length];
OUTPUT[0]=xReal;
for(int j=0;j<xImag.length;j++)
OUTPUT[1][j]=-xImag[j];
return OUTPUT;

	}
	
	private static int bitreverseReference(int j, int nu) {
	    int j2;
	    int j1 = j;
	    int k = 0;
	    for (int i = 1; i <= nu; i++) {
	        j2 = j1 / 2;
	        k = 2 * k + j1 - 2 * j2;
	        j1 = j2;
	    }
	    return k;
	  }
	
	static float[] abs(float[] INPUT, float[] INPUT1)
	{
		float[] OUTPUT=new float[INPUT.length];
		
		for(int i=0;i<INPUT.length;i++)
		{
			OUTPUT[i]=(float)Math.sqrt(INPUT[i]*INPUT[i]+INPUT1[i]*INPUT1[i]);
		}
		
		return OUTPUT;
	}
	
	static float[][] butter(float[][] INPUT,float fa,float fb,float n,float m,float sample_int)
	{
		float[][] OUTPUT=new float[INPUT.length][INPUT[0].length];
		int sample=INPUT[0].length;
		fa=2.5f;
		fb=250;
		n=3;
		m=0;
		float nyquist=(1f/(2*sample_int))*1000;
		float A;
		/*
		for(int i=0;i<sample;i++)
		{
			float ii=((float)i/(float)sample)*nyquist;
			double a=Math.pow(ii, 2*n)/fa;
			double b=(1+Math.pow(ii, 2*n)/fa)*(1+Math.pow(ii, 2*m)/fb);
			A=(float)Math.sqrt(a/b);
			System.out.println(ii+" "+A);
			INPUT[0][i]=A*INPUT[0][i];
			INPUT[1][i]=A*INPUT[1][i];
		}
		*/
		
		for(int i=0;i<sample;i++)
		{
			float ii=((float)i/(float)sample)*(nyquist*2);
			A=1;
		if(ii<3)
			A=0;
		else if(ii>nyquist*2-3)
			A=0;
			OUTPUT[0][i]=A*INPUT[0][i];
			OUTPUT[1][i]=A*INPUT[1][i];
		}
		
		return OUTPUT;
	}
	
	static float rms(float[] INPUT,int start,int end)
	{
		int n=end-start;
		float xn=0;
		for(int i=start;i<end;i++)
		{
			xn=(INPUT[i]*INPUT[i])+xn;
		}
		xn=xn/n;
		float output=(float)Math.sqrt((double)xn);
		return output;
		
	}
	
	static float[][] lowpass(float[][] INPUT,float fa,float sample_int)
	{
		float[][] OUTPUT=new float[INPUT.length][INPUT[0].length];
		int sample=INPUT[0].length;
		float nyquist=(1f/(2*sample_int))*1000;
		float A;
		
		for(int i=0;i<sample;i++)
		{
			float ii=((float)i/(float)sample)*(nyquist*2);
			A=1;
		if(ii<fa)
			A=0;
		else if(ii>nyquist*2-fa)
			A=0;
			OUTPUT[0][i]=A*INPUT[0][i];
			OUTPUT[1][i]=A*INPUT[1][i];
		}
		
		return OUTPUT;
	}
	
	public static int findpow(int a)
	{
		int output=1;
		while(output<a)
		{
			output=output*2;
		}
		return output;
	}
	
	

}
