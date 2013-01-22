function [] = showcircles(imgin, N)
	t = linspace(0, 2*pi, 10);
	imshow(imgin);
	imginGray = rgb2gray(imgin);
	C = circles(imginGray, N);
	hold on;
	for i=1:N
		plot(C(i,3) * cos(t) + C(i,1), C(i,3) * sin(t) + C(i,2), "linewidth", 3);
	end
endfunction