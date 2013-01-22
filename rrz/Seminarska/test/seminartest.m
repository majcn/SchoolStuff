
% dolocite pot do slik ...
pth = './';

ground;

for i = 1:(size(G, 1))

  imn = G{i, 1};
  ant = G{i, 2};

  img = imread([pth imn]);

  regions = recognize(img);

  gN = size(ant, 1);
  rN = size(regions, 1);

  D = overlap(ant(:, 1:4), regions(:, 1:4));

  scoreok = 0;
  scorewr = 0;

  for j = 1:rN
   [m, mi] = min(D(j, :));

   if m <= 0
    scorewr ++;
    continue;
   end

   if (all(rN(j, 5:6) == gN(mi, 5:6)))
     scoreok ++;
   else
     scorewr ++;
   end

  end

  figure;
  imshow(img);
  hold on;
  showresults(ant, [0, 1, 0]);
  showresults(regions, [1, 0, 0]);
  hold off;

  printf('Rezultat za %s - pravilni %f  napacni %f \n \n', imn, scoreok, scorewr);

end


