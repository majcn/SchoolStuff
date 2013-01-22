function [m] = cmoment(imgin, p, q)
m00 = sum(sum(imgin));   %sestejemo vse tocke, ki imajo vrednost > 0....m00 = vse na 0, torej vse 1
[u,v] = ind2sub(size(imgin), find(imgin)); %u so vrstice, v so stolpci....po vrsti
m10 = sum(u); %m10 je vsota stolpcev
m01 = sum(v); %m01 je vsota vrstic
iks = m10/m00;
ips = m01/m00;
m = sum(((u-iks).^p) .* ((v-ips).^q));  %izracunamo (u-x)^p * (v-x)^p za vse u in v
end;