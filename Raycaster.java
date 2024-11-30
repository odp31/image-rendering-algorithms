public class Raycaster {
  public static class Ray {
    public Vector3 origin;
    public Vector3 direction;

    public Ray(Vector3 origin, Vector3 direction) {
      this.origin = origin;
      this.direction = direction.normalize();
    }
  }
  public static class Sphere {
    public Vector3 center;
    public double radius;
    public Sphere(Vector3 center, double radius) {
      this.center = center;
      this.radius = radius;
    }
  }
  public static boolean intersectSphere(Ray ray, Sphere sphere, double[] t) {
    Vector3 L = sphere.center.subtract(ray.origin);
    double tca = L.dot(ray.direction);
    if(tca < 0) {
      return false;
    }
    double d2 = L.dot(L) - tca * tca;
    double r2 = sphere.radius * sphere.radius;
    if(d2 > r2) {
      return false;
    }
    double thc = Math.sqrt(r2 - d2);
    t[0] = tca - thc;
    t[1] = tca + thc;
    return true;
  }
}
