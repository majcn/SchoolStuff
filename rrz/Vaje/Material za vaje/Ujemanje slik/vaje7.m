
colormap gray;

%I = imread('lena.png');
%T = imread('template.png');

%figure;

%subplot(2,2,1);

%tic; M1 = matching(I, T, 'absdiff'); toc;
%imagesc(max(max(M1)) - M1);

%subplot(2,2,2);

%tic; M2 = matching(I, T, 'maxdiff'); toc;
%imagesc(max(max(M2)) - M2);

%subplot(2,2,3);

%tic; M3 = matching(I, T, 'ssd'); toc;
%imagesc(max(max(M3)) - M3);

%subplot(2,2,4);

%tic; M4 = matching(I, T, 'ncc'); toc;
%imagesc(M4);


I = 255 - threshold(imread('numbers.png'), 128);
T = 255 - threshold(imread('number.png'), 128);

subplot(2,2,1);
imshow(I);

subplot(2,2,2);
tic; M5 = matching(I, T, 'ssd'); toc;
imagesc(M5);

D = bwdist(I);
subplot(2,2,3);
imagesc(D);

tic; M6 = matching(I, T, 'chamfer'); toc;
subplot(2,2,4);
imagesc(1 - M6);

