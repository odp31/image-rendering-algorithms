// represents 3D scenes as a grid of voxels (3D pixels)
// can be useful for rendering volumetric data like medical scans or smoke simulations 


import java.awt.image.BufferedImage;
import java.awt.Color;

public class VoxelRenderer {
  private VoxelGrid voxelGrid;
  private Camera camera;

  public VoxelRenderer(VoxelGrild voxelGrid, Camera camera) {
    this.voxelGrid = voxelGrid;
    this.camera = camera;
  }
  public BufferedImage render() {
    int width = camera.getWidth();
    int height = camera.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for(int y = 0; y < height; y++) {
      for(int x = 0; x < width; x++) {
        Ray ray = camera.generateRay(x,y);
        Voxel voxel = raycast(ray);
        Color color = voxel != null ? voxel.getColor() : Color.BLACK;
        image.setRGB(x, y, color.getRGB());
      }
    }
    return image;
  }
  private Voxel raycast(Ray ray) {
    Voxel voxel = null;
    double t = 0.0;
    double tMax = Double.POSITIVE_INFINITY;

    while(t < tMax) {
      int x = (int) ray.origin.x + (int) (t * ray.direction.x);
      int y = (int) ray.origin.y + (int) (t * ray.direction.y);
      int z = (int) ray.origin.z + (int) (t * ray.direction.z);

      if(voxelGrid.isVoxel(x, y, z)) {
        voxel = voxelGrid.getVoxel(x, y, z);
        tMax = t;
      }
      t += 0.5; // adjust step size as needed 
    }
    return voxel;
  }
}
